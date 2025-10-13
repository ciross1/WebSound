package com.webSounds.WebSounds.ServiceInterface;

import com.webSounds.WebSounds.Entity.MusicGenre;
import com.webSounds.WebSounds.Entity.Sounds;
import org.springframework.stereotype.Component;

@Component
public interface SoundsInterfaceService {


    public Sounds saveSound(Sounds sound, Long genreId);


}
