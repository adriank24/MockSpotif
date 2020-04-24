package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.PlaylistSong;

import org.springframework.data.repository.CrudRepository;

public interface PlaylistSongRepository extends CrudRepository<PlaylistSong,Integer> {
    List<PlaylistSong> findByPlaylistId(Integer playlistId);
}