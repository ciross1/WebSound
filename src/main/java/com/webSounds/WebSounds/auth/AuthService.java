package com.webSounds.WebSounds.auth;


import com.webSounds.WebSounds.User.Role;
import com.webSounds.WebSounds.User.User;
import com.webSounds.WebSounds.User.UserRepository;
import com.webSounds.WebSounds.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login (LoginRequest loginRequest){

        // Para verificar el usuario y contraseña:
        // Devuelve el objeto Authentication que es el objeto que representa el usuario autenticado
       Authentication auth  =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        //Para conceder el token al usuario:
       // Esta línea básicamente obtiene los datos del usuario que acaba de ser autenticado.
        // El cast (UserDetails) es necesario porque getPrincipal() devuelve un Object genérico, pero en nuestro flujo sabemos que realmente es un UserDetails
        UserDetails userDetails = (UserDetails)  auth.getPrincipal();
        String token = jwtService.getToken(userDetails);

        //Devolvemos el token:
        return AuthResponse.builder()
                .token(token)
                .build();
    }










    public AuthResponse register(RegisterRequest registerRequest){

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .country(registerRequest.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);




        // Generar token
        // Aquí estoy solamente generando token, no estoy autenticando usuario

        // Generas el token en register como una forma de autenticar al usuario
        // automáticamente después de crear la cuenta, para que no tenga que hacer login inmediatamente.

        String token = jwtService.getToken(user); //User implementa a UserDetails


        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
