package MockSpotify.Spotify;

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

import MockSpotify.Spotify.entities.User;
import MockSpotify.Spotify.repositories.UserRepository;


@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;

  @PostMapping(path="/user/add") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<User> addNewUser (@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    try {
    	User userData = new User();
    	userData.setName(name);
    	userData.setEmail(email);
        userRepository.save(userData);
        return ResponseEntity.ok(userData); 
	}catch (Exception e) {
		return ResponseEntity.notFound().build();
	}
  }

  @GetMapping(path="/user")
  public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
    // This returns a JSON or XML with the users
	  try {
			return ResponseEntity.ok(userRepository.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
  }

  @GetMapping(path="/user/{id}")
  public @ResponseBody ResponseEntity<User> getUser(@PathVariable int id) {
	try {
		User userData = userRepository.findById(id).get();
		return ResponseEntity.ok(userData); 
	}catch (Exception e) {
		return ResponseEntity.notFound().build();
	}
  }
  
  @PutMapping(path="/user/{id}")
  public @ResponseBody ResponseEntity<User> updateUser(@RequestParam String name, @RequestParam String email,
	        @PathVariable int id) {

	  try {
		  User userData = new User();
		  userData.setId(id);
		  userData.setEmail(email);
		  userData.setName(name);
		  userRepository.save(userData);
		  return ResponseEntity.ok().build();
		}catch (Exception e) {
		  return ResponseEntity.notFound().build();
		}
  }
  
  @DeleteMapping(path="/user/{id}")
  public @ResponseBody ResponseEntity<Void> deleteUser(@PathVariable int id){
	try {
	  userRepository.deleteById(id);
	  return ResponseEntity.ok().build();
	}catch (Exception e) {
	  return ResponseEntity.notFound().build();
	}
	  
  }
}