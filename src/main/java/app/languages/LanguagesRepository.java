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

    ArrayList<Languages> findLanguageByMoviesId(Integer movieId);


    //da implementare in main
    //Page<String> findMoviesByLanguage(String language, Pageable pageable);


    @Query(value = "SELECT l.id, l.language " +
            "FROM languages l " +
            "JOIN languages_movies lm ON lm.language_id = l.id " +
            "JOIN movies m ON m.id = lm.movie_id " +
            "GROUP BY l.id, l.language " +
            "ORDER BY COUNT(m.id) DESC " +
            "LIMIT 10", nativeQuery = true)
    ArrayList<Languages> findTop10LanguagesWithId();




    @Query(value = "SELECT lm.type FROM languages_movies lm " +
            "WHERE lm.language_id :languageId", nativeQuery = true)
    Optional<String> findTypeByLanguageId(Integer languageId);




}
