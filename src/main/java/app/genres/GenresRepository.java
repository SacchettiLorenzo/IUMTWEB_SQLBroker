package app.genres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {

    // Trova il genere in base all'ID
    Optional<Genres> findById(Integer id);

    // Trova tutti gli ID in base al genere
    @Query("SELECT g.id FROM Genres g WHERE g.genre = :genre")
    List<Integer> findIdsByGenre(String genre);

    // Trova i 10 generi più popolari
    @Query("SELECT g.genre, COUNT(g.genre) AS count " +
            "FROM Genres g " +
            "GROUP BY g.genre " +
            "ORDER BY count DESC")
    List<Object[]> findTop10Genres();
}
