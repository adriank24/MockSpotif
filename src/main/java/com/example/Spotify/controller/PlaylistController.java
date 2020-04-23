package com.example.Spotify.controller;

import com.example.Spotify.entities.Playlist;
import com.example.Spotify.repositories.PlaylistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/spotif")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;

    @PostMapping(path="/playlist/add")
    public @ResponseBody ResponseEntity<Playlist> addNewPlaylist(@RequestParam String name,
    @RequestParam Integer user_id){
        try{
            Playlist playlistData=new Playlist();
            playlistData.setName(name);
            playlistData.setUser_id(user_id);
            playlistRepository.save(playlistData);
            return ResponseEntity.ok(playlistData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}