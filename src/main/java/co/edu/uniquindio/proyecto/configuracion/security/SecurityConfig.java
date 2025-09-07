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
/*
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para peticiones que no usen formularios
                .cors(cors -> cors.disable()) // Asegúrate de que no haya conflictos de CORS (CORS se maneja en WebConfig)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/user/login").permitAll()
                                .requestMatchers("/api/user/updateInformation").permitAll()// Permitir acceso a /api/user/login sin autenticación
                                .requestMatchers("/api/**").authenticated() // Las demás rutas de /api necesitan autenticación
                                .anyRequest().authenticated()) // El resto de las rutas requieren autenticación
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT no usa sesiones
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ✅ Permitir swagger y api-docs
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // ✅ Permitir todo lo demás de momento
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}

