package app.themes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Repository
public interface ThemesRepository extends JpaRepository<Themes, Integer> {

    @Query(value = "SELECT t.theme " +
            "FROM themes t " +
            "JOIN themes_movies tm ON t.id = tm.theme_id " +
            "WHERE tm.movie_id = :movieId LIMIT 1", nativeQuery = true)
    Optional<String> getThemeByMovieId(@Param("movieId") Integer movieId);

    @Query(value = "SELECT m.name " +
            "FROM movies m " +
            "JOIN themes_movies tm ON m.id = tm.movie_id " +
            "JOIN themes t ON tm.theme_id = t.id " +
            "WHERE t.theme = :theme", nativeQuery = true)
    List<String> getMoviesByTheme(@Param("theme") String theme);

    @Query(value = "SELECT t.theme, COUNT(tm.theme_id) AS count " +
            "FROM themes t " +
            "JOIN themes_movies tm ON t.id = tm.theme_id " +
            "GROUP BY t.theme " +
            "ORDER BY count DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10Themes();

    ArrayList<Themes> findThemesListByMoviesId(Integer movieId);


}

