package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Playlist;
import com.example.Spotify.entities.User;

import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist,Integer> {
    List<Playlist> findByName(String name);
    List<Playlist> findByUser(User user);
    // List<Playlist> findByUser_id(Integer user_id);
}