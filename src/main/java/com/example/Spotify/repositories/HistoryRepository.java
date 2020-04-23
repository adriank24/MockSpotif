package com.example.Spotify.repositories;

import com.example.Spotify.entities.History;

import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Integer> {

}