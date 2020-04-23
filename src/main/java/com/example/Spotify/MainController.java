package com.example.Spotify;

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

import com.example.Spotify.entities.User;
import com.example.Spotify.entities.Genre;
import com.example.Spotify.entities.Label;
import com.example.Spotify.repositories.UserRepository;
import com.example.Spotify.repositories.GenreRepository;
import com.example.Spotify.repositories.LabelRepository;



@Controller // This means that this class is a Controller
@RequestMapping(path="/spotif") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;

  @PostMapping(path="/user/register") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<User> addNewUser (@RequestParam String name
      , @RequestParam String email, @RequestParam String password) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    try {
      User userData = new User();
      String status = "Free";
    	userData.setName(name);
      userData.setEmail(email);
      userData.setPassword(password);
      userData.setStatus(status);
        userRepository.save(userData);
        return ResponseEntity.ok(userData); 
	  }catch (Exception e) {
		  return ResponseEntity.notFound().build();
    }
  }

  

  @PutMapping(path="/subscribe/{id}")
  public @ResponseBody ResponseEntity<User> updateUser(@RequestParam String name, @RequestParam String email,
	        @PathVariable int id, @RequestParam String password) {

	  try {
      User userData = new User();
      String stat = "Premium";
		  userData.setId(id);
		  userData.setEmail(email);
      userData.setName(name);
      userData.setPassword(password);
      userData.setStatus(stat);
		  userRepository.save(userData);
		  return ResponseEntity.ok().build();
		}catch (Exception e) {
		  return ResponseEntity.notFound().build();
		}
  }
  

	  
}