package app.languages;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LanguagesRepository extends JpaRepository<Languages, Integer> {
    //trovo il tipo associato al film
    Optional<Languages> findTypeLanguagesById(Integer id);
    //trovo la lingua associata al film
    Optional<Languages> findLanguageById(Integer id);
    //trovo tutte le lingue associate a un film
    List<Languages> findAllLanguagesById(Integer id);
    //trovo tutte le lingue associate a un tipo
    List<Languages> findAllLanguagesByType(String type);
    //trovo la lista di film con una lingua specifica
    List<Languages> findMovieByLanguage(String language);

    // Query per trovare le 10 lingue più usate
    @Query("SELECT l.language, COUNT(l.language) AS count " +
            "FROM Languages l " +
            "GROUP BY l.language " +
            "ORDER BY count DESC")
    List<Object[]> findTop10Languages();

    // Query per trovare i 5 tipi più usati
    @Query("SELECT l.type, COUNT(l.type) AS count " +
            "FROM Languages l " +
            "GROUP BY l.type " +
            "ORDER BY count DESC")
    List<Object[]> findTop5Types();




}
