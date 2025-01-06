package app.movies;


import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer>, PagingAndSortingRepository<Movies, Integer> {

    Optional<Movies> findById(int id);

    ArrayList<Movies> findByName(String title);

    ArrayList<Movies> findMoviesListByNameIgnoreCaseContaining(String title);

    ArrayList<Movies> findMoviesByMinuteIsBetween(int min, int max);
    ArrayList<Movies> findMoviesByRatingBetween(float min, float max);

    ArrayList<Movies> findAllByOrderByRatingDesc();

    ArrayList<Movies> findMoviesListByActorsId(int actorsId);

    //ArrayList<Movies> findMoviesListByGenreIdOrderByDateDesc(Limit limit);

    Page<Movies> findMoviesByGenresId(Integer genresId, Pageable pageable);

    @Query(value = "SELECT m.id, m.name, m.minute FROM movies m WHERE m.minute IS NOT NULL ORDER BY m.minute DESC LIMIT 10", nativeQuery = true)
    List<Map<Movies, Integer>> findTop10LongestMovies();


    @Query(value = "SELECT m.id, m.name, m.minute FROM movies m WHERE m.minute IS NOT NULL ORDER BY m.minute ASC LIMIT 10", nativeQuery = true)
    List<Map<Movies, Integer>> findTop10ShortestMovies();









}
