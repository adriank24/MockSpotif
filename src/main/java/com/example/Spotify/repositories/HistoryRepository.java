package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.History;
import com.example.Spotify.entities.User;

import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Integer> {
    History findByUserIdAndSongId(User userId, Integer songId);
    List<History> findByUserId(User userId);
}