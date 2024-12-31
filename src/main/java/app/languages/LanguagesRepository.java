package app.languages;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LanguagesRepository extends JpaRepository<Languages, Integer> {

    Optional<Languages> findTypeLanguagesById(Integer id);

    @Query("SELECT l.language FROM Languages l " +
            "JOIN l.movies m " +
            "WHERE m.id = :movieId")
    Optional<String> findLanguageByMovieId(@Param("movieId") Integer movieId);



    @Query("SELECT m.name FROM Movies m " +
            "JOIN m.languages l " +
            "WHERE l.language = :language")
    Page<String> findMoviesByLanguage(@Param("language") String language, Pageable pageable);


    @Query(value = "SELECT l.language, COUNT(m.id) AS count " +
            "FROM languages l " +
            "JOIN languages_movies lm ON lm.language_id = l.id " +
            "JOIN movies m ON m.id = lm.movie_id " +
            "GROUP BY l.language " +
            "ORDER BY count DESC", nativeQuery = true)
    List<Object[]> findTop10Languages();

    @Query(value = "SELECT lm.type FROM languages_movies lm " +
            "WHERE lm.movie_id = :movieId", nativeQuery = true)
    Optional<String> findTypeByMovieId(@Param("movieId") Integer movieId);




}
