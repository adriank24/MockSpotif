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
import com.example.Spotify.entities.Artist;

import java.util.List;


@Controller
@RequestMapping(path="/spotif")
public class LabelController {
    @Autowired
    private LabelRepository labelRepository;
    private SongRepository songRepo;
    private AlbumRepository albumRepo;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private PlaylistSongRepository playlistsongRepo;
    private ArtistRepository artistRepo;
   
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

  public void deleteArtist(int id){
    try {
        Artist artistData = artistRepo.findById(id).get();
        List<Album> albumData = albumRepo.findByArtist(artistData);
        if(albumData!=null){
          for(int x=0;x<albumData.size();x++){
            deleteAlbum(albumData.get(x).getId());
          };
        }
        artistRepo.deleteById(id);
    }catch (Exception e) {
        System.out.println("error");
    }
    
}

    @DeleteMapping(path="/label/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteLabel(@PathVariable int id){
        try{
            Label labelData = labelRepository.findById(id).get();
            List<Artist> artistData = artistRepo.findByLabel(labelData);
            List<Album> albumData = albumRepo.findByLabel(labelData);
            if(albumData!=null){
                for(int i=0;i<albumData.size();i++){
                  deleteAlbum(albumData.get(i).getId());
                };
            }

            if(artistData!=null){
                for(int i=0;i<artistData.size();i++){
                  deleteArtist(artistData.get(i).getId());
                };
            }
            labelRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    

}