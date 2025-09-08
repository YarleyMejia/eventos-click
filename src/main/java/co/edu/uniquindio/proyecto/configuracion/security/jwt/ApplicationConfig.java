package co.edu.uniquindio.proyecto.configuracion.security.jwt;

import co.edu.uniquindio.proyecto.repositorios.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuración de Spring Security para la aplicación.
 * Esta clase define los beans necesarios para la autenticación y
 * el manejo de usuarios dentro del sistema, utilizando JWT y
 * codificación de contraseñas.
 */
@Configuration // Marca la clase como un componente de configuración de Spring
@RequiredArgsConstructor // Genera un constructor con los campos final automáticamente
public class ApplicationConfig {

    // Repositorio de usuarios que permite acceder a los datos de los usuarios
    private final IUserRepository userRepository;

    /**
     * Bean de AuthenticationManager.
     * Se utiliza para manejar la autenticación dentro de Spring Security.
     *
     * @param configuration La configuración de autenticación de Spring
     * @return AuthenticationManager configurado
     * @throws Exception Si ocurre un error al obtener el AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Bean de AuthenticationProvider.
     * Define cómo se deben autenticar los usuarios. En este caso,
     * se utiliza DaoAuthenticationProvider que verifica usuarios
     * usando un UserDetailsService y un PasswordEncoder.
     *
     * @return AuthenticationProvider configurado
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // Configura el UserDetailsService que obtiene los datos de los usuarios
        authenticationProvider.setUserDetailsService(userDetailsService());
        // Configura el PasswordEncoder para validar las contraseñas
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean de PasswordEncoder.
     * Se utiliza para codificar y verificar contraseñas de manera segura.
     *
     * @return PasswordEncoder que implementa BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean de UserDetailsService.
     * Define cómo se cargan los detalles del usuario a partir del email.
     *
     * @return UserDetailsService que obtiene usuarios desde el repositorio
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> (UserDetails) userRepository.findByEmail(email);
    }

}
