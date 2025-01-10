package app.languages;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.Optional;

public interface LanguagesRepository extends JpaRepository<Languages, Integer> {

    ArrayList<Languages> findLanguageByMoviesId(Integer movieId);

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
