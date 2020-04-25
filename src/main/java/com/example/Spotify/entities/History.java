package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name= "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer timePlayed;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "userId", nullable = false)
    private User userId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "songId", nullable = false)
    private Song songId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return this.userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Integer getTimePlayed() {
        return this.timePlayed;
    }

    public void setTimePlayed(Integer timePlayed) {
        this.timePlayed = timePlayed;
    }

    public Song getSongId() {
        return this.songId;
    }

    public void setSongId(Song songId) {
        this.songId = songId;
    }
}