package com.webSounds.WebSounds.auth.config;


import com.webSounds.WebSounds.auth.jwt.JwtAuthentificationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Habilita Spring Security en tu proyecto y Permite configurar reglas personalizadas: rutas públicas, autenticación, autorización, filtros, etc.
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthentificationFilter jwtAuthentificationFilter;

//private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http

                .csrf(csrf ->csrf.disable())


                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                )


                // Dice a Spring como manejar las sesiones del usuario,
                // En este caso con STATELESS, significa que no se guarda nada en el servidor y que cada petición se valida de forma independiente.
                // Es decir,El servidor no guarda sesión.
                //Cada petición se autentica por el JWT que envía el cliente.
                // JWT + STATELESS → Es como llevar tu ticket firmado contigo: cada vez que entras, lo muestras y el guardia (servidor) lo verifica, pero con JWT hay que recordar
                // que no se guarda nada en el servidor. El servidor genera el token JWT cuando el usuario inicia sesión.
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))



      // aquí es donde tu filtro JWT entra en acción para “leer” el token de cada petición.
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();



    }

}
