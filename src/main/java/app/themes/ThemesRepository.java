package app.themes;

import app.languages.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Repository
public interface ThemesRepository extends JpaRepository<Themes, Integer> {

    ArrayList<Themes> findThemesByMoviesId(Integer movieId);

    // questo va dentro movies
    /*
    @Query(value = "SELECT m.name " +
            "FROM movies m " +
            "JOIN themes_movies tm ON m.id = tm.movie_id " +
            "JOIN themes t ON tm.theme_id = t.id " +
            "WHERE t.theme = :theme", nativeQuery = true)
    List<String> getMoviesByTheme(@Param("theme") String theme);
     */

    @Query(value = "SELECT t.id, t.theme " +
            "FROM themes t " +
            "JOIN themes_movies tm ON tm.theme_id = t.id " +
            "JOIN movies m ON m.id = tm.movie_id " +
            "GROUP BY t.id, t.theme " +
            "ORDER BY COUNT(m.id) DESC " +
            "LIMIT 10", nativeQuery = true)
    ArrayList<Themes> findTop10ThemesWithId();



}

