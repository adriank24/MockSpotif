package com.example.Spotify.repositories;

import com.example.Spotify.entities.Artist;
import org.springframework.data.repository.CrudRepository;


public interface ArtistRepository extends CrudRepository<Artist,Integer>{

}