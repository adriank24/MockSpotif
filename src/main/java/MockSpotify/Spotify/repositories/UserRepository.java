package MockSpotify.Spotify.repositories;
import MockSpotify.Spotify.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {

}