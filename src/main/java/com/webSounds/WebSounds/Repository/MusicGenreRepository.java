package com.webSounds.WebSounds.Repository;

import com.webSounds.WebSounds.Entity.MusicGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicGenreRepository extends JpaRepository<MusicGenre, Long> {

    // Opcional: buscar por nombre
    Optional<MusicGenre> findByTitleOrDescription(String titleOfGenre, String description);
}
