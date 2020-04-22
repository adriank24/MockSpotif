package MockSpotify.Spotify.repositories;

import MockSpotify.Spotify.entities.Genre;
import org.springframework.data.repository.CrudRepository;


public interface GenreRepository extends CrudRepository<Genre,Integer>{

}