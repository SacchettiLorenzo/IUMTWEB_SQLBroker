package app.languages;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LanguagesRepository extends JpaRepository<Languages, Integer> {

    Optional<Languages> findTypeLanguagesById(Integer id);

    Optional<Languages> findLanguageById(Integer id);

    Page<Languages> findAllLanguagesById(Integer id, Pageable pageable);

    Page<Languages> findAllLanguagesByType(String type, Pageable pageable);

    Page<Languages> findMovieByLanguage(String language, Pageable pageable);

    @Query("SELECT l.language, COUNT(l.language) AS count " +
            "FROM Languages l " +
            "GROUP BY l.language " +
            "ORDER BY count DESC")
    List<Object[]> findTop10Languages();

    @Query("SELECT l.type, COUNT(l.type) AS count " +
            "FROM Languages l " +
            "GROUP BY l.type " +
            "ORDER BY count DESC")
    List<Object[]> findTop5Types();


}
