package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Label;
import org.springframework.data.repository.CrudRepository;

public interface LabelRepository extends CrudRepository<Label,Integer> {
    List<Label> findByName(String name);
}