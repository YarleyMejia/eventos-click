package co.edu.uniquindio.proyecto.configuracion.security;

import co.edu.uniquindio.proyecto.configuracion.security.jwt.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de Spring Security para la aplicación.
 * Define cómo se protegen las rutas, qué endpoints requieren autenticación
 * y cómo se integran los filtros de JWT.
 */
/*
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Filtro de autenticación JWT personalizado
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    // Proveedor de autenticación (configurado en ApplicationConfig)
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // Deshabilita CSRF ya que se trabaja con JWT y no con formularios
                .csrf(csrf -> csrf.disable())
                // Deshabilita CORS (puede manejarse en WebConfig)
                .cors(cors -> cors.disable())
                // Configuración de acceso a endpoints
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/user/login").permitAll() // Permite login sin autenticación
                                .requestMatchers("/api/user/updateInformation").permitAll() // Permite actualizar info sin login
                                .requestMatchers("/api/**").authenticated() // Resto de endpoints /api requieren autenticación
                                .anyRequest().authenticated()) // Todo lo demás también requiere autenticación
                // Configura la aplicación para no usar sesiones (JWT es stateless)
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Asigna el AuthenticationProvider definido previamente
                .authenticationProvider(authenticationProvider)
                // Agrega el filtro JWT antes del filtro de username/password por defecto
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
*/

/**
 * Versión simplificada de SecurityConfig para desarrollo o pruebas.
 * Actualmente permite todas las rutas, excepto que se habilita Swagger para documentación.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de Spring Security.
     * Actualmente:
     * - Deshabilita CSRF
     * - Permite el acceso a Swagger UI y API docs
     * - Permite todo lo demás sin autenticación
     *
     * @param http HttpSecurity para configurar los filtros
     * @return SecurityFilterChain configurada
     * @throws Exception Si ocurre un error durante la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita CSRF ya que no se usan formularios
                .csrf(csrf -> csrf.disable())
                // Configuración de acceso a endpoints
                .authorizeHttpRequests(auth -> auth
                        // Permitir documentación de Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Permitir todas las demás rutas (para desarrollo)
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
