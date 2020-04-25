package com.example.Spotify.controller;

import java.util.List;

import com.example.Spotify.entities.Playlist;
import com.example.Spotify.entities.PlaylistSong;
import com.example.Spotify.entities.User;
import com.example.Spotify.repositories.PlaylistRepository;
import com.example.Spotify.repositories.PlaylistSongRepository;
import com.example.Spotify.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/spotif")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @PostMapping(path="/playlist/add")
    public @ResponseBody ResponseEntity<Playlist> addNewPlaylist(@RequestParam String name,
    @RequestParam String email){
        try{
            Playlist playlistData=new Playlist();
            playlistData.setName(name);
            User userData = userRepository.findByEmail(email).get(0);
            playlistData.setUser(userData);
            playlistRepository.save(playlistData);
            return ResponseEntity.ok(playlistData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/playlist/get/{email}")
    public @ResponseBody ResponseEntity<List<Playlist>> getPlaylistUser(@PathVariable String email){
        try{
            List<User> user = userRepository.findByEmail(email);
            User id_user=user.get(0);
            List<Playlist> playlistData = playlistRepository.findByUser(id_user);
            return ResponseEntity.ok(playlistData);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/playlist/delete/{namePlaylist}")
    public @ResponseBody ResponseEntity<Void> deletePlaylist(@PathVariable String namePlaylist){
        try {
            List<Playlist> playlist = playlistRepository.findByName(namePlaylist);
            Playlist playlistData=playlist.get(0);
            List<PlaylistSong> playlistSong = playlistSongRepository.findByPlaylist(playlistData);

            for(int i=0; i<playlistSong.size();i++){
                playlistSongRepository.deleteById(playlistSong.get(i).getId());
            }
            playlistRepository.deleteById(playlistData.getId());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}