package com.artesanos.artesanos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Configura usuarios en memoria
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // Configura el encriptado de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura seguridad de endpoints
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para APIs REST
            .authorizeHttpRequests(auth -> auth
                // ENDPOINTS PÚBLICOS
                .requestMatchers("/api/productos/**").permitAll()
                
                // ENDPOINTS PROTEGIDOS
                .requestMatchers("/api/artesanos/**").authenticated()
                
                // Cualquier otro endpoint requiere autenticación
                .anyRequest().authenticated()
            )
            .httpBasic(); // Autenticación básica
        return http.build();
    }
}


