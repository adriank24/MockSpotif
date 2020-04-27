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

import com.example.Spotify.entities.Artist;
import com.example.Spotify.entities.Label;
import com.example.Spotify.entities.Song;
import com.example.Spotify.entities.Album;
import com.example.Spotify.entities.PlaylistSong;
import com.example.Spotify.entities.History;
import com.example.Spotify.repositories.ArtistRepository;
import com.example.Spotify.repositories.LabelRepository;
import com.example.Spotify.repositories.SongRepository;
import com.example.Spotify.repositories.AlbumRepository;
import com.example.Spotify.repositories.PlaylistSongRepository;
import com.example.Spotify.repositories.HistoryRepository;


import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/spotif") // This means URL's start with /demo (after Application path)
public class ArtistController {
  @Autowired
  private ArtistRepository artistRepo;

  @Autowired
  private LabelRepository labelRepository;
  private SongRepository songRepo;
  private AlbumRepository albumRepo;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private PlaylistSongRepository playlistsongRepo;


  @PostMapping(path="/artist/add") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Artist> addNewArtist (@RequestParam String name, @RequestParam int label) {
    try {
      Artist artistData = new Artist();
        artistData.setName(name);
        Label labelData = labelRepository.findById(label).get();
        artistData.setLabel(labelData);
        artistRepo.save(artistData);
        return ResponseEntity.ok(artistData); 
	  }catch (Exception e) {
		  return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path="/artist/get")
  public @ResponseBody ResponseEntity<Iterable<Artist>> getAllArtist() {
    try {
        return ResponseEntity.ok(artistRepo.findAll()); 
    }catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path="/artist/get/{artistId}")
  public @ResponseBody ResponseEntity <Artist> getArtist(@PathVariable int artistId) {
    try {
        Artist artistData = artistRepo.findById(artistId).get();
        return ResponseEntity.ok(artistData); 
    }catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }


  @GetMapping(path="/artist/get/label/{labelId}")
  public @ResponseBody ResponseEntity <List<Artist>> getArtistByLabel(@PathVariable int labelId){
    try{
      Label labelData = labelRepository.findById(labelId).get();
      List<Artist> artistData = artistRepo.findByLabel(labelData);

      return ResponseEntity.ok(artistData);
    }
    catch (Exception e){
      return ResponseEntity.notFound().build();
    }

  }

      public void deleteSong(int songId){
        try {
            Song songData = songRepo.findById(songId).get();
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
            songRepo.deleteById(songId);
        }catch (Exception e) {
            System.out.println("Error");
          }
    }

    public void deleteAlbum(int id){
      try {
          Album albumData = albumRepo.findById(id).get();
          List<Song> songData = songRepo.findByAlbum(albumData);
          if(songData != null){
              for(int i=0;i<songData.size();i++){
                  deleteSong(songData.get(i).getId());
              };
          }
          albumRepo.deleteById(id);
      }catch (Exception e) {
          System.out.println("error");
      }
      
  }

  @DeleteMapping(path="/artist/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteArtist(@PathVariable int id){
        try {
            Artist artistData = artistRepo.findById(id).get();
            List<Album> albumData = albumRepo.findByArtist(artistData);
            List<Song> songData = songRepo.findByArtist(artistData);
            if(songData!=null){
              for(int x=0;x<songData.size();x++){
                deleteSong(songData.get(x).getId());
              };
            };

            if(albumData!=null){
              for(int x=0;x<albumData.size();x++){
                deleteAlbum(albumData.get(x).getId());
              };
            };

            artistRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PutMapping(path="/artist/update/{id}")
    public @ResponseBody ResponseEntity<Artist> updateArtist(@PathVariable int id, @RequestParam String name,
    @RequestParam int label) {
        try {
        Artist artistData = new Artist();
        artistData.setId(id);
        artistData.setName(name);
        Label labelData = labelRepository.findById(label).get();
        artistData.setLabel(labelData);
        artistRepo.save(artistData);
        return ResponseEntity.ok(artistData);   
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }


}