package com.example.Spotify.repositories;

import com.example.Spotify.entities.Genre;
import org.springframework.data.repository.CrudRepository;


public interface GenreRepository extends CrudRepository<Genre,Integer>{

}