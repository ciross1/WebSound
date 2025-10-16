package com.webSounds.WebSounds.ServiceInterface;

import com.webSounds.WebSounds.DTO.SoundRequest;
import com.webSounds.WebSounds.Entity.MusicGenre;
import com.webSounds.WebSounds.Entity.Sounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SoundsInterfaceService {


    public Sounds saveSound(SoundRequest soundRequest);
    public List<Sounds> getAllSounds();

}
