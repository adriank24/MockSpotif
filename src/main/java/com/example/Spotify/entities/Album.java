package com.example.Spotify.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name= "album")
public class Album implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "label", nullable = false)
    private Label label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "artist", nullable = false)
    private Artist artist;

    @OneToMany
    (
        targetEntity = Song.class,
        mappedBy = "album",
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
            song.setAlbum(this);
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

    @JsonManagedReference
    public Artist getArtist(){
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @JsonManagedReference
    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

}