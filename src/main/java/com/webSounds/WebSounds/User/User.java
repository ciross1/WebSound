package com.webSounds.WebSounds.User;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;


    private String firstName;
    private String lastName;
    private String country;

    @JsonIgnore
    // evita que se devuelva la contraseña en las respuestas JSON
    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;











// Spring Security necesita una forma uniforme de acceder a los datos de un usuario autenticado (sin importar de dónde venga: base de datos, API, LDAP, etc.).
//Por eso define la interfaz UserDetails, que tiene varios métodos que tu entidad User debe implementar.

    // Esta clase User actúa como el “modelo de usuario” que Spring usa para autenticación y autorización.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
