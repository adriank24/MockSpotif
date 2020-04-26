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


import com.example.Spotify.entities.Album;
import com.example.Spotify.entities.Label;
import com.example.Spotify.entities.Artist;
import com.example.Spotify.repositories.AlbumRepository;
import com.example.Spotify.repositories.LabelRepository;
import com.example.Spotify.repositories.ArtistRepository;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/spotif") // This means URL's start with /demo (after Application path)
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepo;

    @Autowired
    private LabelRepository labelRepo;

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

    @GetMapping(path="/album/get")
    public @ResponseBody ResponseEntity<Iterable<Album>> getAllAlbum (){
        try{
            return ResponseEntity.ok(albumRepository.findAll());
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/album/get/{id}")
    public @ResponseBody ResponseEntity<Album> getAlbumById(@PathVariable int albumId){
        try{
            Album albumData = albumRepository.findById(albumId).get();
            return ResponseEntity.ok(albumData);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/album/get/artist/{artistName}")
    public @ResponseBody ResponseEntity<List<Album>> getAlbumByArtist(@PathVariable String artistName){
        try{
            List<Artist> artistData = artistRepo.findByName(artistName);
            List<Album> albumData = albumRepository.findByArtist(artistData.get(0));
            return ResponseEntity.ok(albumData);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/album/get/label/{labelName}")
    public @ResponseBody ResponseEntity<List<Album>> getAlbumByLabel(@PathVariable String labelName){
        try{
            Label labelData = labelRepo.findByName(labelName);
            List<Album> albumData = albumRepository.findByLabel(labelData);
            return ResponseEntity.ok(albumData);
        }
        catch(Exception e){
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

    @DeleteMapping(path="/album/delete/artist/{id}")
    public @ResponseBody ResponseEntity<Void> deleteAlbumByArtist(@PathVariable int artistId){
        try{
            Artist dataArtist = artistRepo.findById(artistId).get();
            albumRepository.deleteByArtist(dataArtist);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}