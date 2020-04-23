package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HistorySong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_history;
    private Integer id_song;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_history() {
        return this.id_history;
    }

    public void setId_history(Integer id_history) {
        this.id_history = id_history;
    }

    public Integer getId_song() {
        return this.id_song;
    }

    public void setId_song(Integer id_song) {
        this.id_song = id_song;
    }

}