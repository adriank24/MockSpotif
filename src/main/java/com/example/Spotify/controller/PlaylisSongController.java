package com.example.Spotify.controller;

import java.util.List;

import com.example.Spotify.entities.Playlist;
import com.example.Spotify.entities.PlaylistSong;
import com.example.Spotify.entities.Song;
import com.example.Spotify.repositories.PlaylistRepository;
import com.example.Spotify.repositories.PlaylistSongRepository;
import com.example.Spotify.repositories.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/spotif")
public class PlaylisSongController {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @PostMapping(path="/playlist/add/song")
    public @ResponseBody ResponseEntity<PlaylistSong> addSongToPlaylist(@RequestParam String name_song,
    @RequestParam String name_playlist){
        try{
            List<Song>  song = songRepository.findByName(name_song);
            Integer id_song=song.get(0).getId();

            List<Playlist> playlist = playlistRepository.findByName(name_playlist);
            Integer id_playlist=playlist.get(0).getId();
            
            PlaylistSong playlistSongData= new PlaylistSong();
            playlistSongData.setPlaylist_id(id_playlist);
            playlistSongData.setSong_id(id_song);
            playlistSongRepository.save(playlistSongData);
            return ResponseEntity.ok(playlistSongData); 

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}