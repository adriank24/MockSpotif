package com.example.Spotify.repositories;

import com.example.Spotify.entities.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {

}