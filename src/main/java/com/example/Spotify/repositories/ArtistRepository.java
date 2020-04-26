package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Artist;
import com.example.Spotify.entities.Label;
import org.springframework.data.repository.CrudRepository;


public interface ArtistRepository extends CrudRepository<Artist,Integer>{
    List<Artist> findByName(String name);
    List<Artist> findByLabel(Label label);

}