package com.webSounds.WebSounds.ServiceInterface;

import com.webSounds.WebSounds.DTO.GenreRequest;
import com.webSounds.WebSounds.Entity.MusicGenre;

import java.util.List;

public interface GenresInterfaceService {

    public MusicGenre createGenre(GenreRequest request);
    public List<MusicGenre> getAllGenres();
    public int deleteOneGenreById(Long id);
}
