package app.movies;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    Optional<Movies> findById(int id);

    //todo: multiple result possible
    Optional<Movies> findByName(String title);

    ArrayList<Movies> findMoviesByMinuteIsBetween(int min, int max);
    ArrayList<Movies> findMoviesByRatingBetween(float min, float max);

    ArrayList<Movies> findAllByOrderByRatingDesc();

    @Query(value = "SELECT m FROM Movies m WHERE m.rating IS NOT NULL ORDER BY m.rating DESC LIMIT 10")
    ArrayList<Movies> findTop10OrderByRatingDesc();

    ArrayList<Movies> findMoviesListByActorsId(int actorsId);






}
