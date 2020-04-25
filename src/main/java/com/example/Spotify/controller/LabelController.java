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

import java.util.List;

import com.example.Spotify.entities.Artist;
import com.example.Spotify.entities.Song;
import com.example.Spotify.entities.Label;
import com.example.Spotify.repositories.ArtistRepository;
import com.example.Spotify.repositories.SongRepository;
import com.example.Spotify.repositories.LabelRepository;

@Controller
@RequestMapping(path="/spotif")
public class LabelController {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    Label temp = new Label();

    @PostMapping(path="/label/add")
    public @ResponseBody ResponseEntity<Label> addLabel(@RequestParam String labelName){
        try{
            Label labelData=new Label();
            labelData.setName(labelName);
            labelRepository.save(labelData);
            return ResponseEntity.ok(labelData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/label/get")
    public @ResponseBody ResponseEntity<Iterable<Label>> getAllLabel(){
        try{
            return ResponseEntity.ok(labelRepository.findAll());
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/label/get/{labelId}")
    public @ResponseBody ResponseEntity<Label> getLabel(@PathVariable int labelId){
        try{
            Label labelData = labelRepository.findById(labelId).get();
            return ResponseEntity.ok(labelData);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path="/label/update/{id}")
    public @ResponseBody ResponseEntity<Label> updateLabel(@PathVariable int id, @RequestParam String labelName) {
        try {
        Label labelData = new Label();
        labelData.setId(id);
        labelData.setName(labelName);
        labelRepository.save(labelData);
        return ResponseEntity.ok(labelData);   
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }

    @GetMapping(path="/label/view/song/{labelName}")
    public @ResponseBody ResponseEntity<List<Song>> getSongbyAlbum(@PathVariable String labelName){
        try{
            Label labelData = labelRepository.findByName(labelName).get(0);
            List<Song> songData = songRepository.findByLabel(labelData);
            return ResponseEntity.ok(songData);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping(path="/playlist/get/{email}")
    // public @ResponseBody ResponseEntity<List<Playlist>> getPlaylistUser(@PathVariable String email){
    //     try{
    //         List<User> user = userRepository.findByEmail(email);
    //         Integer id_user=user.get(0).getId();
    //         List<Playlist> playlistData = playlistRepository.findByUser(id_user);
    //         return ResponseEntity.ok(playlistData);
    //     }catch(Exception e){
    //         return ResponseEntity.notFound().build();
    //     }
    // }



    // @DeleteMapping(path = "/playlist/delete/{namePlaylist}")
    // public @ResponseBody ResponseEntity<Void> deletePlaylist(@PathVariable String namePlaylist){
    //     try {
    //         List<Playlist> playlist = playlistRepository.findByName(namePlaylist);
    //         Integer id_playlist=playlist.get(0).getId();
    //         List<PlaylistSong> playlistSong = playlistSongRepository.findByPlaylistId(id_playlist);

    //         for(int i=0; i<playlistSong.size();i++){
    //             playlistSongRepository.deleteById(playlistSong.get(i).getId());
    //         }
    //         playlistRepository.deleteById(id_playlist);
    //         return ResponseEntity.ok().build();
    //     }catch (Exception e){
    //         return ResponseEntity.notFound().build();
    //     }
    // }

}