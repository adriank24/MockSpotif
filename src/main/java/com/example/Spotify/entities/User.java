package com.example.Spotify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String status;

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