package com.example.Spotify.controller;

import java.util.List;

import com.example.Spotify.entities.Album;
import com.example.Spotify.entities.Artist;
import com.example.Spotify.entities.Genre;
import com.example.Spotify.entities.History;
import com.example.Spotify.entities.Label;
import com.example.Spotify.entities.Song;
import com.example.Spotify.entities.User;
import com.example.Spotify.entities.PlaylistSong;
import com.example.Spotify.entities.History;
import com.example.Spotify.repositories.AlbumRepository;
import com.example.Spotify.repositories.ArtistRepository;
import com.example.Spotify.repositories.GenreRepository;
import com.example.Spotify.repositories.HistoryRepository;
import com.example.Spotify.repositories.LabelRepository;
import com.example.Spotify.repositories.SongRepository;
import com.example.Spotify.repositories.UserRepository;
import com.example.Spotify.repositories.PlaylistSongRepository;
import com.example.Spotify.repositories.HistoryRepository;

import com.example.Spotify.controller.PlaylistSongController;

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

    @Autowired
    private AlbumRepository albumRepository;
    
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private PlaylistSongRepository playlistsongRepo;

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
            Album albumData = albumRepository.findById(album).get();
            songData.setAlbum(albumData);
            Label labelData = labelRepository.findById(label).get();
            songData.setLabel(labelData);
            Genre genreData = genreRepository.findById(genre).get();
            songData.setGenre(genreData);
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
            return ResponseEntity.ok(song.get(0)); 
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/song/get/artist/{name}")
    public @ResponseBody ResponseEntity<List<Song>> getSongbyArtist(@PathVariable String name){
        try{
            List<Artist> artistData= artistRepository.findByName(name);
            List<Song> songData = songRepository.findByArtist(artistData.get(0));

            return ResponseEntity.ok(songData);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(path="/song/get/label/{name}")
    public @ResponseBody ResponseEntity<List<Song>> getSongByLabel(@PathVariable String labelName){
        try{
            Label labelData = labelRepository.findByName(labelName);
            List<Song> songData = songRepository.findByLabel(labelData);
            return ResponseEntity.ok(songData);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/song/get/album/{name}")
    public @ResponseBody ResponseEntity<List<Song>> getSongbyAlbum(@PathVariable String name){
        try{
            Album albumData= albumRepository.findByName(name);
            List<Song> songData = songRepository.findByAlbum(albumData);

            return ResponseEntity.ok(songData);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    //play lagu
    @PutMapping(path="/song/play/{name}")
    public @ResponseBody ResponseEntity<Song> playSong(@PathVariable String name, @RequestParam String email){
        try {
            User userData= userRepository.findByEmail(email).get(0);
            List<Song> song = songRepository.findByName(name);
            Song songData = song.get(0);
            Integer temp = songData.getPlayed();
            songData.setPlayed(temp+1);
            songRepository.save(songData);
            History history = historyRepository.findByUserIdAndSongId(userData, songData.getId());

            if(history!=null){
                Integer tempHist = history.getTimePlayed();
                history.setTimePlayed(tempHist+1);
                historyRepository.save(history);
            }else {
                History newHistory = new History();
                newHistory.setSongId(songData);
                newHistory.setTimePlayed(1);
                newHistory.setUserId(userData);
                historyRepository.save(newHistory);
            }
            return ResponseEntity.ok().build();
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }

    @PutMapping(path="/song/update/{id}")
    public @ResponseBody ResponseEntity<Song> updateSong(@RequestParam Integer artist,
    @RequestParam Integer album, @RequestParam Integer label, @RequestParam Integer genre,
    @RequestParam Double duration, @RequestParam String name,@PathVariable int id){
        try {
            Song songTemp = songRepository.findById(id).get();
            Song songData = new Song();
            songData.setId(id);
            Artist artistData = artistRepository.findById(artist).get();
            songData.setArtist(artistData);
            Album albumData = albumRepository.findById(album).get();
            songData.setAlbum(albumData);
            Label labelData = labelRepository.findById(label).get();
            songData.setLabel(labelData);
            Genre genreData = genreRepository.findById(genre).get();
            songData.setGenre(genreData);
            songData.setDuration(duration);
            songData.setName(name);
            songData.setPlayed(songTemp.getPlayed());
            songRepository.save(songData);
            return ResponseEntity.ok().build();
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }

    @DeleteMapping(path="/song/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteSong(@PathVariable int songId){
        try {
            Song songData = songRepository.findById(songId).get();
            List<History> historyData = historyRepository.findBySongId(songData);
            List<PlaylistSong> playlistSongs = playlistsongRepo.findBySong(songData);
            if(historyData!=null){
                for(int j=0;j<historyData.size();j++){
                    historyRepository.deleteById(historyData.get(j).getId());
                }
            }

            if(playlistSongs!=null){
                for(int j=0;j<playlistSongs.size();j++){
                    playlistsongRepo.deleteById(playlistSongs.get(j).getId());
                }
            }
            songRepository.deleteById(songId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }

}