package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.Album;
import com.example.Spotify.entities.Artist;

import org.springframework.data.repository.CrudRepository;

import aj.org.objectweb.asm.Label;


public interface AlbumRepository extends CrudRepository<Album,Integer> {
    List<Album> findByArtist(Artist artist);
    List<Album> findByLabel(Label label);
    Album findByName(String name);
}