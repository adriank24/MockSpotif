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
import com.example.Spotify.entities.Song;
import com.example.Spotify.repositories.AlbumRepository;
import com.example.Spotify.repositories.LabelRepository;
import com.example.Spotify.repositories.ArtistRepository;
import com.example.Spotify.repositories.SongRepository;
import com.example.Spotify.controller.MusicController;

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

    @Autowired 
    private SongRepository songRepo;

    private MusicController songController;

    @PostMapping(path="/album/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<Album> addNewAlbum (@RequestParam String name, @RequestParam int label, @RequestParam int artist) {
        try {
            Album albumData = new Album();
            Label labelData = labelRepo.findById(label).get();
            albumData.setLabel(labelData);
            Artist artistData = artistRepo.findById(artist).get();
            albumData.setArtist(artistData);
            albumData.setName(name);
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
    public @ResponseBody ResponseEntity<Album> getAlbumById(@PathVariable int id){
        try{
            Album albumData = albumRepository.findById(id).get();
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
            Album albumData = albumRepository.findById(id).get();
            List<Song> songData = songRepo.findByAlbum(albumData);
            if(songData != null){
                for(int i=0;i<songData.size();i++){
                    songController.deleteSong(songData.get(i).getId());
                };
            }
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
        Label labelData = labelRepo.findById(label).get();
        albumData.setLabel(labelData);
        Artist artistData = artistRepo.findById(artist).get();
        albumData.setArtist(artistData);
        albumRepository.save(albumData);
        return ResponseEntity.ok(albumData);   
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }

}