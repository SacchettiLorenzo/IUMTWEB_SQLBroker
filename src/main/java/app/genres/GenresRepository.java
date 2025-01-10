package app.genres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer>, PagingAndSortingRepository<Genres, Integer> {

    Optional<Genres> findById(Integer id);

    ArrayList<Genres> findGenresListByMoviesId(Integer id);

    @Query(value = "SELECT g.id ,g.genre, COUNT(gm.genre_id) AS movie_count " +
            "FROM genres_movies gm " +
            "JOIN genres g ON gm.genre_id = g.id " +
            "GROUP BY g.id, g.genre " +
            "ORDER BY movie_count DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Map<Genres, Object>> findTop10MostPopularGenres();




}
