package app.themes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ThemesRepository extends JpaRepository<Themes, Integer> {

    Optional<Themes> findById(Integer id);

    @Query("SELECT t.id FROM Themes t WHERE t.theme = :theme")
    Optional<Integer> findIdByTheme(String theme);

    @Query("SELECT t.theme, COUNT(t.theme) AS count " +
            "FROM Themes t " +
            "GROUP BY t.theme " +
            "ORDER BY count DESC")
    List<Object[]> findTop10Themes();
}

