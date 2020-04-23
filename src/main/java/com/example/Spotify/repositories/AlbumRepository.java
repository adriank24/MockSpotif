package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Album;
import org.springframework.data.repository.CrudRepository;


public interface AlbumRepository extends CrudRepository<Album,Integer> {
    List<Album> findByArtist(Integer artist);
    List<Album> findByLabel(Integer label);
    List<Album> findByName(String name);
}