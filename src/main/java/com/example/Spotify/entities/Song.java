package com.example.Spotify.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name= "song")
public class Song implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer album;
    private Integer label;
    private Integer genre;
    private double duration;
    private String name;
    private Integer played;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "artist", nullable = false)
    private Artist artist;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonManagedReference
    public Artist getArtist(){
        return artist;
    }

    public void setArtist(Artist artist) {
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