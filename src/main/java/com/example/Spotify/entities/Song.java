package com.example.Spotify.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private double duration;
    private String name;
    private Integer played;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "genre", nullable = false)
    private Genre genre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "label", nullable = false)
    private Label label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "album", nullable = false)
    private Album album;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "artist", nullable = false)
    private Artist artist;

    @OneToMany
    (
        targetEntity = PlaylistSong.class,
        mappedBy = "song",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<PlaylistSong> playlistSongs;

    @JsonBackReference
    public Set<PlaylistSong> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(Set<PlaylistSong> playlistSongs){
        this.playlistSongs = playlistSongs;
        for(PlaylistSong playlistSong : playlistSongs){
            playlistSong.setSong(this);
        }
    }

    @OneToMany
    (
        targetEntity = History.class,
        mappedBy = "songId",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<History> histories;

    @JsonBackReference
    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories){
        this.histories = histories;
        for(History history : histories){
            history.setSongId(this);
        }
    }

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

    @JsonManagedReference
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @JsonManagedReference
    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @JsonManagedReference
    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
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