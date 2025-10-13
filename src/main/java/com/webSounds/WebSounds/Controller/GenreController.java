package com.webSounds.WebSounds.Controller;

import com.webSounds.WebSounds.DTO.GenreRequest;
import com.webSounds.WebSounds.Entity.MusicGenre;
import com.webSounds.WebSounds.Service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/genre")
public class GenreController {

    private final GenreService service;


    @PostMapping("/create")
    public ResponseEntity<MusicGenre> createGenre(@RequestBody GenreRequest genreRequest){
        MusicGenre genre = service.createGenre(genreRequest);
        return ResponseEntity.ok(genre);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MusicGenre>> getAllGenres(){

        return ResponseEntity.ok(service.getAllGenres());
    }

    @DeleteMapping("/delete/{id}")
    // Void porque indica que no hay contenido en la respuesta, es coherente con HTTP 2024 No Content
    public ResponseEntity<Void>deleteGenre(@PathVariable Long id){

        service.deleteOneGenreById(id);

        return ResponseEntity.noContent().build(); // 202 -> esto significa “la solicitud fue exitosa, pero no hay contenido para devolver”.
        // Alternativa: devolver HTTP 200 con el objeto eliminado si quieres que el cliente lo reciba, pero 204 es más limpio y RESTful.

    }




}
