package com.webSounds.WebSounds.Service;

import com.webSounds.WebSounds.DTO.SoundRequest;
import com.webSounds.WebSounds.Entity.MusicGenre;
import com.webSounds.WebSounds.Entity.Sounds;
import com.webSounds.WebSounds.Repository.SoundsRepository;
import com.webSounds.WebSounds.Repository.MusicGenreRepository;
import com.webSounds.WebSounds.ServiceInterface.SoundsInterfaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor // generates a constructor with all final fields.
public class SoundsService implements SoundsInterfaceService {

    private final SoundsRepository soundsRepository;
    private final MusicGenreRepository genreRepository;


    @Override
    public Sounds saveSound(SoundRequest soundRequest) {



        // 1. Retrieve all genres from DB by their IDs
        Set<MusicGenre> genres = new HashSet<>(genreRepository.findAllById(soundRequest.getGenreIds())); //Busca por ID el género que hemos enviado como request

        //2. Validate at least one genre was found
        if(genres.isEmpty()){
            throw new EntityNotFoundException("One or more genres were not found in the database");
        }

        Sounds sound = Sounds.builder()
                .title(soundRequest.getTitle())
                .description(soundRequest.getTitle())
                .durationSeconds(soundRequest.getDurationSeconds())
                .price(soundRequest.getPrice())
                .fileUrl(soundRequest.getFileUrl())
                .genres(genres)
                .tags(soundRequest.getTags())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();

        return soundsRepository.save(sound);
    }

    @Override
    public List<Sounds> getAllSounds() {
        return soundsRepository.findAll();
    }


}
