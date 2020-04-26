package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Playlist;
import com.example.Spotify.entities.PlaylistSong;
import com.example.Spotify.entities.Song;

import org.springframework.data.repository.CrudRepository;

public interface PlaylistSongRepository extends CrudRepository<PlaylistSong,Integer> {
    List<PlaylistSong> findByPlaylist(Playlist playlist);
    PlaylistSong findByPlaylistAndSong(Playlist playlist, Song song);
    List<PlaylistSong> findBySong(Song song);
}