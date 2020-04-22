package MockSpotify.Spotify.repositories;

import MockSpotify.Spotify.entities.Album;
import org.springframework.data.repository.CrudRepository;


public interface AlbumRepository extends CrudRepository<Album,Integer> {

}