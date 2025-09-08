package co.edu.uniquindio.proyecto.configuracion.security.jwt;

import co.edu.uniquindio.proyecto.configuracion.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticación JWT que se ejecuta una vez por cada solicitud.
 * Se encarga de validar el token JWT, extraer el usuario y establecer la autenticación
 * en el contexto de seguridad de Spring Security.
 */
@Component // Marca la clase como un componente de Spring para inyección de dependencias
@RequiredArgsConstructor // Genera automáticamente un constructor con los campos final
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    // Servicio para manejar tokens JWT
    private final JwtService jwtService;

    // Servicio para cargar los detalles del usuario desde la base de datos
    private final UserDetailsService userDetailsService;

    /**
     * Método principal del filtro que se ejecuta por cada solicitud HTTP.
     * Valida el token JWT y establece la autenticación en el contexto de seguridad.
     *
     * @param request  La solicitud HTTP entrante
     * @param response La respuesta HTTP
     * @param filterChain Cadena de filtros para pasar la solicitud al siguiente filtro
     * @throws ServletException Si ocurre un error del servlet
     * @throws IOException Si ocurre un error de entrada/salida
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extrae el token JWT de la solicitud
        final String token = getTokenFromRequest(request);
        final String email;

        // Si no hay token, pasa la solicitud al siguiente filtro
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtiene el email del usuario desde el token
        email = jwtService.getEmailFromToken(token);

        // Si hay un email y no hay autenticación establecida aún
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Carga los detalles del usuario desde la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Verifica si el token es válido
            if (jwtService.isTokenValid(token, userDetails)) {
                // Crea el objeto de autenticación de Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                // Asocia detalles de la solicitud a la autenticación
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Verifica roles y permisos según la URL solicitada
                if (checkUserRole(userDetails, request)) {
                    // Establece la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    // Si el usuario no tiene permisos, responde con 403 Forbidden
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                    return;
                }
            }
        }

        // Pasa la solicitud al siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    /**
     * Método auxiliar para verificar si el usuario tiene permisos según su rol.
     *
     * @param userDetails Detalles del usuario autenticado
     * @param request La solicitud HTTP
     * @return true si el usuario tiene el rol necesario, false de lo contrario
     */
    private boolean checkUserRole(UserDetails userDetails, HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        // Dependiendo de la URL, se habilita el acceso por rol
        if (requestURI.equals("/user/")) {
            // Solo usuarios con rol "admin" pueden acceder a /user/
            return userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("admin"));
        }

        // Por defecto, deniega el acceso
        return false;
    }

    /**
     * Método auxiliar para extraer el token JWT del encabezado Authorization.
     *
     * @param request La solicitud HTTP
     * @return El token JWT si está presente, null si no existe
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        // Obtiene el valor del encabezado Authorization
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Verifica si el encabezado tiene texto y empieza con "Bearer "
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            // Retorna el token sin el prefijo "Bearer "
            return authHeader.substring(7);
        }

        // Retorna null si no se encuentra token
        return null;
    }
}
