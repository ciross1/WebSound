package com.webSounds.WebSounds.Controller;


import com.webSounds.WebSounds.DTO.SoundRequest;
import com.webSounds.WebSounds.Entity.Sounds;
import com.webSounds.WebSounds.Service.SoundsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/sound")
@RequiredArgsConstructor
public class SoundController {

    private final SoundsService service;





    @GetMapping("/getAllSounds")
   public List<Sounds> getAllSounds(){

        return service.getAllSounds();
    }



    @PostMapping("/saveSound")
    public ResponseEntity<String> saveSound(@RequestBody SoundRequest soundRequest){

         service.saveSound(soundRequest);

        return ResponseEntity.ok("Sound has been successfully uploaded");
    }
}
