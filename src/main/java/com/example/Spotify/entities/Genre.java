package com.example.Spotify.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany
    (
        targetEntity = Song.class,
        mappedBy = "genre",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Song> songs;

    @JsonBackReference
    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs){
        this.songs = songs;
        for(Song song : songs){
            song.setGenre(this);
        }
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}