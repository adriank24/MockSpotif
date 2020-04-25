package com.example.Spotify.repositories;

import java.util.List;
import java.util.Optional;

import com.example.Spotify.entities.Song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {
    List<Song> findByName(String name);

    Page<Song> findByArtist(int artist, Pageable pageable);
    Optional<Song> findByIdAndArtist(int id, int artist);
    
}