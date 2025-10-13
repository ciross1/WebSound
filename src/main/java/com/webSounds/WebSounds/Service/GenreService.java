package com.webSounds.WebSounds.Service;

import com.webSounds.WebSounds.DTO.GenreRequest;
import com.webSounds.WebSounds.Entity.MusicGenre;
import com.webSounds.WebSounds.Repository.MusicGenreRepository;
import com.webSounds.WebSounds.ServiceInterface.GenresInterfaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService implements GenresInterfaceService {


    private final MusicGenreRepository genreRepository;



    @Override
    public MusicGenre createGenre(GenreRequest request) {

        // Evitar duplicados
        genreRepository.findByTitleOrDescription(request.getTitle(), request.getDescription())
                .ifPresent(g -> {
                    throw new RuntimeException("Genre already exists"); }
                );

        MusicGenre genre = MusicGenre.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        return genreRepository.save(genre);

    }

    @Override
    public List<MusicGenre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public int deleteOneGenreById(Long id) {

        // Buscar el género o lanzar excepción si no existe
        MusicGenre genreToBeDeleted = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));

        genreRepository.deleteById(id);

       return 1;




    }
}
