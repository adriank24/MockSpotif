package com.example.Spotify.repositories;

import com.example.Spotify.entities.Album;
import org.springframework.data.repository.CrudRepository;


public interface AlbumRepository extends CrudRepository<Album,Integer> {

}