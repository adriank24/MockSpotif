package com.example.Spotify.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    // private Date tgl_dibuat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user", nullable = false)
    private User user;

    @OneToMany
    (
        targetEntity = PlaylistSong.class,
        mappedBy = "playlist",
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
            playlistSong.setPlaylist(this);
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

    // public Date getTgl_dibuat() {
    //     return this.tgl_dibuat;
    // }

    // public void setTgl_dibuat(Date tgl_dibuat) {
    //     this.tgl_dibuat = tgl_dibuat;
    // }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}