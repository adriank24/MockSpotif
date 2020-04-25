package com.example.Spotify.controller;

import java.util.List;

import com.example.Spotify.entities.Artist;
import com.example.Spotify.entities.Song;
import com.example.Spotify.repositories.ArtistRepository;
import com.example.Spotify.repositories.SongRepository;

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

@Controller
@RequestMapping(path="/spotif")
public class MusicController {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ArtistRepository artistRepository;
    public Song temp;
    
    @PostMapping(path="/song/add")
    public @ResponseBody ResponseEntity<Song> addNewSong(@RequestParam Integer artist,
    @RequestParam Integer album, @RequestParam Integer label, @RequestParam Integer genre,
    @RequestParam Double duration, @RequestParam String name){
        try{
            int played =0 ;
            Song songData = new Song();
            Artist artistData = artistRepository.findById(artist).get();
            songData.setArtist(artistData);
            songData.setAlbum(album);
            songData.setLabel(label);
            songData.setGenre(genre);
            songData.setDuration(duration);
            songData.setName(name);
            songData.setPlayed(played);
            songRepository.save(songData);
            return ResponseEntity.ok(songData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Search Lagu
    @GetMapping(path="/song/get/{name}")
    public @ResponseBody ResponseEntity<Song> getSongbyName(@PathVariable String name) {
        try {
            List<Song> song = songRepository.findByName(name);
            temp=song.get(0);
            return ResponseEntity.ok(song.get(0)); 
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



    // //Milih Lagu, by id
    // @GetMapping(path="/song/get/{id}")
    // public @ResponseBody ResponseEntity<Song> selectSong(@PathVariable int id) {
    //     try {
    //         Song songData = songRepository.findById(id).get();
            
    //         return ResponseEntity.ok(songData); 
    //     }catch (Exception e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    //play lagu
    @PutMapping(path="/song/play/{name}")
    public @ResponseBody ResponseEntity<Song> playSong(@PathVariable String name){
        try {
            List<Song> song = songRepository.findByName(name);
            Song songData = song.get(0);
            Integer temp = songData.getPlayed();
            songData.setPlayed(temp+1);
            songRepository.save(songData);
            return ResponseEntity.ok().build();
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }

    @PutMapping(path="/song/update/{id}")
    public @ResponseBody ResponseEntity<Song> updateSong(@RequestParam Integer artist,
    @RequestParam Integer album, @RequestParam Integer label, @RequestParam Integer genre,
    @RequestParam Double duration, @RequestParam String name, @RequestParam Integer played
    ,@PathVariable int id){
        try {
            Song songData = new Song();
            songData.setId(id);
            Artist artistData = artistRepository.findById(artist).get();
            songData.setArtist(artistData);
            songData.setAlbum(album);
            songData.setLabel(label);
            songData.setGenre(genre);
            songData.setDuration(duration);
            songData.setName(name);
            songData.setPlayed(played);
            songRepository.save(songData);
            return ResponseEntity.ok().build();
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }

    @DeleteMapping(path="/song/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteSong(@PathVariable int id){
        try {
            songRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }
}