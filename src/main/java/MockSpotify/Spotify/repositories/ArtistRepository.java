package MockSpotify.Spotify.repositories;

import MockSpotify.Spotify.entities.Artist;
import org.springframework.data.repository.CrudRepository;


public interface ArtistRepository extends CrudRepository<Artist,Integer>{

}