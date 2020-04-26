package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Album;
import com.example.Spotify.entities.Artist;
import com.example.Spotify.entities.Label;
import com.example.Spotify.entities.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {
    List<Song> findByName(String name);
    List<Song> findByArtist(Artist artist);
    List<Song> findByAlbum(Album album);
    List<Song> findByLabel(Label label);
    Void deleteByArtist(Artist artist);
}