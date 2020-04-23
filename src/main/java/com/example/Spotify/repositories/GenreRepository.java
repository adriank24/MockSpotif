package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Genre;
import org.springframework.data.repository.CrudRepository;


public interface GenreRepository extends CrudRepository<Genre,Integer>{
    List<Genre> findByName(String name);
}