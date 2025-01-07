package app.movies;


import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
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

    @Query(value = """
    SELECT m.id, m.name, m.minute
    FROM movies m
    INNER JOIN countries_movies cm ON m.id = cm.movie_id
    INNER JOIN genres_movies gm ON m.id = gm.movie_id
    INNER JOIN languages_movies lm ON m.id = lm.movie_id
    WHERE m.minute IS NOT NULL
    AND (:countryId IS NULL OR cm.country_id = :countryId)
    AND (:genreId IS NULL OR gm.genre_id = :genreId)
    AND (:languageId IS NULL OR lm.language_id = :languageId)
    GROUP BY m.id, m.name, m.minute
    ORDER BY m.minute DESC
    LIMIT 10
    """, nativeQuery = true)
    List<Map<Movies, Integer>> findTop10LongestMovies(@Param("countryId") Integer countryId,
                                                      @Param("genreId") Integer genreId,
                                                      @Param("languageId") Integer languageId);


    @Query(value = """
    SELECT m.id, m.name, m.minute
    FROM movies m
    INNER JOIN countries_movies cm ON m.id = cm.movie_id
    INNER JOIN genres_movies gm ON m.id = gm.movie_id
    INNER JOIN languages_movies lm ON m.id = lm.movie_id
    WHERE m.minute IS NOT NULL
    AND (:countryId IS NULL OR cm.country_id = :countryId)
    AND (:genreId IS NULL OR gm.genre_id = :genreId)
    AND (:languageId IS NULL OR lm.language_id = :languageId)
    GROUP BY m.id, m.name, m.minute
    ORDER BY m.minute ASC
    LIMIT 10
    """, nativeQuery = true)
    List<Map<Movies, Integer>> findTop10ShortestMovies(@Param("countryId") Integer countryId,
                                                       @Param("genreId") Integer genreId,
                                                       @Param("languageId") Integer languageId);

    /*
    @Query(value = "SELECT m.id, m.name, m.rating FROM movies m WHERE m.rating IS NOT NULL ORDER BY m.rating DESC LIMIT 10", nativeQuery = true)
    List<Map<Movies, Double>> findTop10BestMovies();
     */

    @Query(value = """
    SELECT m.id, m.name, m.rating
    FROM movies m
    INNER JOIN countries_movies cm ON m.id = cm.movie_id
    INNER JOIN genres_movies gm ON m.id = gm.movie_id
    INNER JOIN languages_movies lm ON m.id = lm.movie_id
    WHERE m.rating IS NOT NULL
    AND (:countryId IS NULL OR cm.country_id = :countryId)
    AND (:genreId IS NULL OR gm.genre_id = :genreId)
    AND (:languageId IS NULL OR lm.language_id = :languageId)
    GROUP BY m.id, m.name, m.rating
    ORDER BY m.rating ASC
    LIMIT 10
    """, nativeQuery = true)
    List<Map<Movies, Double>> findTop10WorstMoviesByFilters(@Param("countryId") Integer countryId,
                                                   @Param("genreId") Integer genreId,
                                                   @Param("languageId") Integer languageId);

    @Query(value = """
    SELECT m.id, m.name, m.rating
    FROM movies m
    INNER JOIN countries_movies cm ON m.id = cm.movie_id
    INNER JOIN genres_movies gm ON m.id = gm.movie_id
    INNER JOIN languages_movies lm ON m.id = lm.movie_id
    WHERE m.rating IS NOT NULL
    AND (:countryId IS NULL OR cm.country_id = :countryId)
    AND (:genreId IS NULL OR gm.genre_id = :genreId)
    AND (:languageId IS NULL OR lm.language_id = :languageId)
    GROUP BY m.id, m.name, m.rating
    ORDER BY m.rating DESC
    LIMIT 10
    """, nativeQuery = true)
    List<Map<Movies, Double>> findTop10BestMoviesByFilters(
            @Param("countryId") Integer countryId,
            @Param("genreId") Integer genreId,
            @Param("languageId") Integer languageId
    );







}
