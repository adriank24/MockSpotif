package com.example.Spotify.controller;

import java.util.List;

import com.example.Spotify.entities.Playlist;
import com.example.Spotify.entities.User;
import com.example.Spotify.repositories.PlaylistRepository;
import com.example.Spotify.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping(path="/playlist/add")
    public @ResponseBody ResponseEntity<Playlist> addNewPlaylist(@RequestParam String name,
    @RequestParam Integer user_id){
        try{
            Playlist playlistData=new Playlist();
            playlistData.setName(name);
            playlistData.setUser(user_id);
            playlistRepository.save(playlistData);
            return ResponseEntity.ok(playlistData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/playlist/get/{userName}")
    public @ResponseBody ResponseEntity<List<Playlist>> getPlaylistUser(@PathVariable String userName){
        try{
            List<User> user = userRepository.findByName(userName);
            Integer id_user=user.get(0).getId();
            List<Playlist> playlistData = playlistRepository.findByUser(id_user);
            return ResponseEntity.ok(playlistData);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}