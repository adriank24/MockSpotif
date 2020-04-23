package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer artist;
    private Integer album;
    private Integer label;
    private Integer genre;
    private double duration;
    private String name;
    private Integer played;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtist() {
        return this.artist;
    }

    public void setArtist(Integer artist) {
        this.artist = artist;
    }

    public Integer getAlbum() {
        return this.album;
    }

    public void setAlbum(Integer album) {
        this.album = album;
    }

    public Integer getLabel() {
        return this.label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Integer getGenre() {
        return this.genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayed() {
        return this.played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }



}