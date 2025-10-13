package com.webSounds.WebSounds.Controller;


import com.webSounds.WebSounds.Entity.Sounds;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/sound")
public class SoundController {

    @GetMapping("/sounds")
    public String getAllSounds(){
        return "all sounds are okay";
    }

    @PostMapping("/uploadSound")
    public ResponseEntity<String> saveSound(@RequestBody Sounds soundsObj){

        return ResponseEntity.ok("Sound has been successfully uploaded");
    }
}
