package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.History;
import com.example.Spotify.entities.User;
import com.example.Spotify.entities.Song;

import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Integer> {
    History findByUserIdAndSongId(User userId, Song songId);
    List<History> findByUserId(User userId);
    List<History> findBySongId(Song songId);
}