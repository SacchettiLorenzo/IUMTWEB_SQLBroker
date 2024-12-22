package app.actors;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ActorsRepository extends JpaRepository<Actors,Integer> {

    Optional<Actors> findById(Integer id);
    Optional<Actors> findByName(String name);

    ArrayList<Actors> findActorsListByMoviesId(Integer id);

}
