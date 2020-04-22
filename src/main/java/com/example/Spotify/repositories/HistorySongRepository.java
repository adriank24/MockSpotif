package com.example.Spotify.repositories;

import com.example.Spotify.entities.HistorySong;

import org.springframework.data.repository.CrudRepository;

public interface HistorySongRepository extends CrudRepository<HistorySong, Integer> {

}