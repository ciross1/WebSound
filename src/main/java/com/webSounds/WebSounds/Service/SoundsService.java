package com.webSounds.WebSounds.Service;

import com.webSounds.WebSounds.Entity.MusicGenre;
import com.webSounds.WebSounds.Entity.Sounds;
import com.webSounds.WebSounds.Repository.SoundsRepository;
import com.webSounds.WebSounds.Repository.MusicGenreRepository;
import com.webSounds.WebSounds.ServiceInterface.SoundsInterfaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor // generates a constructor with all final fields.
public class SoundsService implements SoundsInterfaceService {

    private final SoundsRepository soundsRepository;
    private final MusicGenreRepository genreRepository;



    @Override
    public Sounds saveSound(Sounds sound, Long genreId) {


        // Buscar el género existente
        MusicGenre genre = genreRepository.findById(genreId)
                .orElseThrow(()-> new RuntimeException("Genre not found"));

        // Asociarlo al sonido
        sound.setGenres(Set.of(genre));

        Sounds soundToBeSaved = soundsRepository.save(sound);



        return soundToBeSaved;
    }
}
