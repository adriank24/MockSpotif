package com.example.Spotify.entities;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String status;

    @OneToMany
    (
        targetEntity = Playlist.class,
        mappedBy = "user",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Playlist> playlists;

    @JsonBackReference
    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setUsers(Set<Playlist> playlists){
        this.playlists = playlists;
        for(Playlist playlist : playlists){
            playlist.setUser(this);
        }
    }

    @OneToMany
    (
        targetEntity = History.class,
        mappedBy = "userId",
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
            history.setUserId(this);
        }
    }

    public Integer getId() {
        return id;
        }
    public void setId(final Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String pass){
        this.password = pass;
    }

    public String getPassword(){
        return password;
    }

    public void setStatus(final String stat){
        this.status = stat;
    }

    public String getStatus(){
        return status;
    }




}