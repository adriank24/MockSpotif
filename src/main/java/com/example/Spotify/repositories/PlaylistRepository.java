package com.example.Spotify.repositories;

import com.example.Spotify.entities.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist,Integer> {

}