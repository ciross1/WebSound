package com.webSounds.WebSounds.auth.config;


import com.webSounds.WebSounds.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


    private final UserRepository userRepository;


// En tu configuración actual con Spring Security estos beans se llaman automáticamente; no necesitas invocarlos manualmente en otra clase.
    // userDetailsService y passwordEncoder y se llaman automáticamente gracias a AuthenticationManager que es como un juez.

  @Bean
  // Dado un username, devuelve el usuario correspondiente desde la base de datos, y si no existe, lanza una excepción:
  // Le dice a Spring Security cómo encontrar un usuario en la base de datos cuando alguien intenta iniciar sesión o cuando validas un JWT.
    public UserDetailsService userDetailsService(){
   //username -> es una expresión lambda
      return username -> userRepository.findByUsername(username)
              .orElseThrow(()-> new UsernameNotFoundException("User not found"));
  }

  @Bean
  // Cuando un usuario se registra o inicia sesión la contraseña se encripta usando Bcrypt antes de guardarse en la base de datos.
  // Spring Security usa este bean para encriptar y verificar contraseñas.
  //Por qué no lo ves usado directamente:
    public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }


  // configura el mecanismo de autenticación que Spring Security usará para validar credenciales de usuarios:
  // En Spring Security, el AuthenticationManager es el componente central que se encarga de autenticar usuarios.
  // Recibe las credenciales (username y contraseña).
  //Llama interna y automáticamente a tu UserDetailsService para obtener el usuario.
  //Usa tu PasswordEncoder para verificar que la contraseña ingresada coincida.
  //Devuelve un objeto Authentication si todoo es correcto, o lanza una excepción si falla.
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();
  }



}
