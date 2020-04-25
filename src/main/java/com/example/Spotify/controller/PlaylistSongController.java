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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/spotif")
public class PlaylistSongController {
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
            Song songData=song.get(0);

            List<Playlist> playlist = playlistRepository.findByName(name_playlist);
            
            PlaylistSong playlistSongData= new PlaylistSong();
            playlistSongData.setPlaylist(playlist.get(0));
            playlistSongData.setSong(songData);
            playlistSongRepository.save(playlistSongData);
            return ResponseEntity.ok(playlistSongData); 

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/playlist/get/song/{name}")
    public @ResponseBody ResponseEntity<List<PlaylistSong>> getSongByPlaylistName(@PathVariable String name) {
        try{
            List<Playlist> playlist = playlistRepository.findByName(name);
            List<PlaylistSong> playlistSongs = playlistSongRepository.findByPlaylist(playlist.get(0));

            return ResponseEntity.ok(playlistSongs);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/playlist/delete/song/{namePlaylist}/{nameSong}")
    public @ResponseBody ResponseEntity<Void> deleteSongFromPlaylist(@PathVariable String namePlaylist, @PathVariable String nameSong){
        try{
            List<Playlist> playlist = playlistRepository.findByName(namePlaylist);
            Playlist playlistData=playlist.get(0);
            Song song = songRepository.findByName(nameSong).get(0);
            PlaylistSong playlistSong = playlistSongRepository.findByPlaylistAndSong(playlistData, song);
            playlistSongRepository.deleteById(playlistSong.getId());
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
}