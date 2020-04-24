package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer songId;
    private Integer playlistId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return this.songId;
    }

    public void setSong_id(Integer songId) {
        this.songId = songId;
    }

    public Integer getPlaylistId() {
        return this.playlistId;
    }

    public void setPlaylist_id(Integer playlistId) {
        this.playlistId = playlistId;
    }

}