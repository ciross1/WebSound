package com.webSounds.WebSounds.auth.jwt;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


// El objetivo de esta clase es crear el token cuando un usuario inicia sesión y firma el token con una clave secreta para que no pueda
// Ser alterado. Este token se usa luego para validar que el usuario está autenticado en futuras peticiones
// Sin necesidad de volver a iniciar sesión.
// En pocas palabras -> es el servicio encargado de crear tokens de seguridad (JWT) para manejar la autenticación en tu aplicación.
@Service
public class JwtService {

   //Esto no es la clave en sí, sino una representación codificada (texto seguro) en base64
   // Con esta clave se firman los tokens JWT
   // Si alguien accede al código, podría ver esta clave y comprometer la seguridad del sistema.
   @Value("${jwt.secret}")
   private String SECRET_KEY ;


   // SecretKey es el tipo de objeto que representa la clave secreta que se usará para firmar y validar el token JWT.
   private SecretKey key;


   // Método público que usa las claims extra vacías por defecto
   public String getToken(UserDetails user){
      return getToken(new HashMap<>(), user);
   }

   //Con este método es donde geenramos el token
   private String getToken(Map<String, Object> extraClaims, UserDetails user){
      return Jwts.builder()
              .setClaims(extraClaims) // añade información extra (claims) dentro del token, por ejemplo roles, permisos u otros datos del usuario.
              .setSubject(user.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *  24))
              .signWith(key, SignatureAlgorithm.HS256) // firma el token con la SecretKey
              .compact();
      //Esto generá el token final
   }




   //Este metodo  genera una clave secreta que usara para firmar y validar. En este meetodo decodificamos la clave para obtener la clave real en bytes,
   // y luego construyendo una clave criptográfica (SecretKey) válida para firmar JWT usando HMAC (por ejemplo, HS256).
   // Este método se ejecuta automáticamente después de la inyección
   @PostConstruct
   private void init (){
      byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);

      this.key = Keys.hmacShaKeyFor(keyBytes);
   }

   public SecretKey getKey(){

      return key;
   }
}
