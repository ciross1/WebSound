package com.webSounds.WebSounds.DTO;

import lombok.Data;


@Data
public class GenreRequest {

    // DTO significa Data Transfer Object
    // o Objeto de Transferencia de Datos. Su propósito principal es transportar datos entre capas de la aplicación,
    // En este caso, transferimos datos al repositorio de MusicGenre

    private String title;
    private String description;
}
