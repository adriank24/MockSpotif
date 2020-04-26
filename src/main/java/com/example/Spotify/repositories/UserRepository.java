package com.example.Spotify.repositories;

import java.util.List;

import com.example.Spotify.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}