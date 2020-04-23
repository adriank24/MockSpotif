package com.example.Spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Spotify.entities.Genre;
import com.example.Spotify.repositories.GenreRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/spotif") // This means URL's start with /demo (after Application path)
public class GenreController {
  @Autowired

    private GenreRepository genreRepo;

    @PostMapping(path="/genre/add") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Genre> addNewGenre (@RequestParam String name) {
    try {
      Genre genreData = new Genre();
      genreData.setName(name);
      genreRepo.save(genreData);
        return ResponseEntity.ok(genreData); 
	  }catch (Exception e) {
		  return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path="/genre/get")
  public @ResponseBody ResponseEntity<Iterable<Genre>> getAllGenre() {
	try {
	    return ResponseEntity.ok(genreRepo.findAll()); 
	}catch (Exception e) {
		return ResponseEntity.notFound().build();
	}
  }

  @DeleteMapping(path="/genre/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteGenre(@PathVariable int id){
        try {
            genreRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    // @PutMapping(path="/genre/update/{id}")
    // public @ResponseBody ResponseEntity<Genre> updateGenre(@PathVariable int id, @RequestParam String name) {
    //     try {
    //     Genre genreData = new Genre();
    //     genreData.setId(id);
    //     genreData.setName(name);
    //     return ResponseEntity.ok(genreData);   
    //       }catch (Exception e) {
    //         return ResponseEntity.notFound().build();
    //       }
    // }



}