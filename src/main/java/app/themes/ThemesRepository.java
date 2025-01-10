package app.themes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface ThemesRepository extends JpaRepository<Themes, Integer> {

    ArrayList<Themes> findThemesByMoviesId(Integer movieId);

    @Query(value = "SELECT t.id, t.theme " +
            "FROM themes t " +
            "JOIN themes_movies tm ON tm.theme_id = t.id " +
            "JOIN movies m ON m.id = tm.movie_id " +
            "GROUP BY t.id, t.theme " +
            "ORDER BY COUNT(m.id) DESC " +
            "LIMIT 10", nativeQuery = true)
    ArrayList<Themes> findTop10ThemesWithId();



}

