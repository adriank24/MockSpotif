package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Artist;
import org.springframework.data.repository.CrudRepository;

import aj.org.objectweb.asm.Label;


public interface ArtistRepository extends CrudRepository<Artist,Integer>{
    List<Artist> findByName(String name);
    List<Artist> findByLabel(Label label);
}