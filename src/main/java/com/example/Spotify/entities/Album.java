package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer artist;
    private Integer label;

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

    public Integer getArtist() {
        return this.artist;
    }

    public void setArtist(Integer artist) {
        this.artist = artist;
    }

    public Integer getLabel() {
        return this.label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

}