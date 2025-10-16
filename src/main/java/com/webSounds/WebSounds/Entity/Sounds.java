package com.webSounds.WebSounds.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Sounds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int durationSeconds;


// Relación de muchos a muchos entre dos entidades.
// Un sonido puede tener muchos géneros y un género puede tener muchos sonidos
// @JoinTable define la tabla intermedia que Hibernate creará en la base de datos para manejar la relación Many-to-Many.
    @ManyToMany
    @JoinTable(
            name = "sound_genre", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "sound_id"), // Columna que apunta al id de la entidad Sound.
            inverseJoinColumns = @JoinColumn(name = "genre_id") // Columna que apunta al id de la entidad MusicGenre
            // inverseJoinColumns → corresponde a la entidad “externa” o la otra parte de la relación.
    )
    private Set<MusicGenre> genres; //  aquí cada Sound puede tener muchos Géneros, por eso usamos una colección:
    // Solo hace falta un Set en Sound porque cada sonido puede tener varios géneros, y con eso ya podemos
    // buscar y relacionar todos los géneros sin que MusicGenre necesite saber qué sonidos tiene.
    // Por cierto Set no puede tener valores repetidos, en cambio, List sí que puede tener valores repetidos.
    private BigDecimal price;

    @Pattern(regexp =  ".*\\.mp3$", message = "File URL must end with .mp3")
    private String fileUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    // Crea automáticamente una tabla auxiliar llamada sound_tags: Esta vez NO hemos usado many to many, sino @ElementCollection
    // que se usa cuando quieres guardar una lista de valores simples (por ejemplo, List<String> o Set<Integer>) dentro de una entidad,
    //pero esos valores no son otra entidad completa con su propio @Id
    @ElementCollection
    @CollectionTable(
            name = "sound_tags",   // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "sound_id") // FK hacia sounds
    )
    @Column(name = "tag") //Columna donde se guarda cada palabra
    private List<String> tags;
    private boolean isActive;


}
