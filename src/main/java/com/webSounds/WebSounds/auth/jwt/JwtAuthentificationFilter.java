package com.webSounds.WebSounds.auth.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// Esta clase es un filtro de seguridad JWT que se ejecuta en cada petición
// HTTP para verificar si el usuario está autenticado usando un token JWT.


@Component
// OncePerRequestFilter asegura que este filtro se ejecute una sola vez por cada solicitud HTTP.
public class JwtAuthentificationFilter extends OncePerRequestFilter {




    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {




        // Permitir acceso libre a rutas públicas (como login o registro)
        // Si el usuario está accediendo a rutas que
        // empiezan con /auth (por ejemplo /auth/login o /auth/register),
        // entonces no se valida token porque son endpoints públicos.
        String path = request.getServletPath();
        if(path.equals("/auth") || path.startsWith("/auth/")){
            filterChain.doFilter(request, response);
            return;
        }

    // Extraer el token de la cabecera
        final String token = getTokenFromRequest(request);


        filterChain.doFilter(request, response);
    }





//    Este méetodo busca el token JWT que debería estar en la cabecera HTTP:
    private String getTokenFromRequest(HttpServletRequest request){

        // obtiene el valor del encabezado HTTP llamado Authorization de la petición que llega del servidor
        // Cuando se utiliza JWT, el cliente (como Postman) envía el token así:
        //Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // valida que la cabecera existe y empieza con "Bearer ".
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }

        // Si no hay encabezado o no se encontró el token nos dará null
        return null;

    }
}
