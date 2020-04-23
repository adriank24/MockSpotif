package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    // private Date tgl_dibuat;
    private Integer user;

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

    public Integer getUser() {
        return this.user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
    
}