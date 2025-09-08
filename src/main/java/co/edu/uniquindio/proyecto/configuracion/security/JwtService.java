package co.edu.uniquindio.proyecto.configuracion.security;

import co.edu.uniquindio.proyecto.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Servicio para generar y validar tokens JWT.
 * Proporciona métodos para crear tokens a partir de usuarios,
 * extraer información de los tokens y validar su autenticidad y expiración.
 */
@Service // Marca la clase como un componente de servicio de Spring
public class JwtService {

    // Clave secreta utilizada para firmar los tokens JWT
    private static final String SECRET_KEY = "98745632112365478945456156465146416541561454";

    /**
     * Genera un token JWT para un usuario dado.
     * Incluye claims adicionales como rol, nombre y correo electrónico.
     *
     * @param user Usuario para el cual se genera el token
     * @return Token JWT en formato String
     */
    public String getToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getName());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        return getToken(claims, user);
    }

    /**
     * Genera el token JWT utilizando claims personalizados y el usuario.
     *
     * @param extraClaims Claims adicionales que se agregarán al token
     * @param user Detalles del usuario
     * @return Token JWT firmado
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims) // Agrega los claims adicionales
                .setSubject(user.getUsername()) // Establece el sujeto del token (username/email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Expiración del token
                .signWith(getKey(), SignatureAlgorithm.HS256) // Firma el token con la clave secreta
                .compact(); // Devuelve el token como String
    }

    /**
     * Obtiene la clave secreta en formato Key para firmar/verificar tokens.
     *
     * @return Clave secreta como objeto Key
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extrae el correo electrónico (username) del token JWT.
     *
     * @param token Token JWT
     * @return Correo electrónico del usuario
     */
    public String getEmailFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    /**
     * Extrae el rol del usuario desde el token JWT.
     *
     * @param token Token JWT
     * @return Rol del usuario
     */
    public String getRoleFromToken(String token) {
        return getClaims(token, claims -> claims.get("role", String.class));
    }

    /**
     * Verifica si el token es válido para un usuario específico.
     * Compara el email del token con el del usuario y revisa la expiración.
     *
     * @param token Token JWT
     * @param userDetails Detalles del usuario
     * @return true si el token es válido, false si no lo es
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Obtiene todos los claims contenidos en el token JWT.
     *
     * @param token Token JWT
     * @return Claims extraídos del token
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Método genérico para obtener un claim específico del token usando un resolver.
     *
     * @param token Token JWT
     * @param claimsResolver Función para extraer un claim específico
     * @param <T> Tipo de dato del claim
     * @return Valor del claim solicitado
     */
    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene la fecha de expiración del token JWT.
     *
     * @param token Token JWT
     * @return Fecha de expiración
     */
    private Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    /**
     * Verifica si el token ha expirado.
     *
     * @param token Token JWT
     * @return true si el token está expirado, false si aún es válido
     */
    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
