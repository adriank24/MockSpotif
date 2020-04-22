package MockSpotify.Spotify.repositories;

import MockSpotify.Spotify.entities.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {

}