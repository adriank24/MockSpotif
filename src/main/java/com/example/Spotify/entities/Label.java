package com.example.Spotify.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name= "label")
public class Label implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany
    (
        targetEntity = Artist.class,
        mappedBy = "label",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Artist> artists;

    @JsonBackReference
    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtist(Set<Artist> artists){
        this.artists = artists;
        for(Artist artist : artists){
            artist.setLabel(this);
        }
    }

    @OneToMany
    (
        targetEntity = Song.class,
        mappedBy = "label",
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
            song.setLabel(this);
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