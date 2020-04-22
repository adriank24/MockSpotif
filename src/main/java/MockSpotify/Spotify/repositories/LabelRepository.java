package MockSpotify.Spotify.repositories;

import MockSpotify.Spotify.entities.Label;
import org.springframework.data.repository.CrudRepository;

public interface LabelRepository extends CrudRepository<Label,Integer> {

}