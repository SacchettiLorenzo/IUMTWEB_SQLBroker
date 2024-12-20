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
    Optional<Movies> findByName(String title);

    List<Movies> findMoviesByMinuteIsBetween(int min, int max);
    List<Movies> findMoviesByRatingBetween(float min, float max);

    ArrayList<Movies> findAllByOrderByRatingDesc();

    @Query(value = "SELECT m FROM Movies m ORDER BY m.rating DESC LIMIT 10")
    ArrayList<Movies> findTop10OrderByRatingDesc();


}
