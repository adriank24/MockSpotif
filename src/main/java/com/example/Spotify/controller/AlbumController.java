package com.example.Spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Spotify.entities.User;
import com.example.Spotify.entities.Album;
import com.example.Spotify.entities.Genre;
import com.example.Spotify.entities.Label;
import com.example.Spotify.repositories.UserRepository;
import com.example.Spotify.repositories.AlbumRepository;
import com.example.Spotify.repositories.GenreRepository;
import com.example.Spotify.repositories.LabelRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/spotif") // This means URL's start with /demo (after Application path)
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping(path="/album/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<Album> addNewAlbum (@RequestParam String name, @RequestParam int label, @RequestParam int artist) {
        try {
            Album albumData = new Album();
            albumData.setName(name);
            albumData.setLabel(label);
            albumData.setArtist(artist);
            albumRepository.save(albumData);
            return ResponseEntity.ok(albumData); 
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path="/album/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteAlbum(@PathVariable int id){
        try {
            albumRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PutMapping(path="/album/update/{id}")
    public @ResponseBody ResponseEntity<Album> updateAlbum(@PathVariable int id, @RequestParam String name,
    @RequestParam int label, @RequestParam int artist) {
        try {
        Album albumData = new Album();
        albumData.setId(id);
        albumData.setName(name);
        albumData.setLabel(label);
        albumData.setArtist(artist);
        albumRepository.save(albumData);
        return ResponseEntity.ok(albumData);   
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }
}