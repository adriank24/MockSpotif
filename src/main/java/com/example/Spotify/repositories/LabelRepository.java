package com.example.Spotify.repositories;

import com.example.Spotify.entities.Label;
import org.springframework.data.repository.CrudRepository;

public interface LabelRepository extends CrudRepository<Label,Integer> {

}