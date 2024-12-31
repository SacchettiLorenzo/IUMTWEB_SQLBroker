package app.genres;

import app.movies.Movies;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer>, PagingAndSortingRepository<Genres, Integer> {

    Optional<Genres> findById(Integer id);

    @Query(value = "SELECT g.genre, COUNT(gm.genre_id) AS genreCount " +
            "FROM genres g " +
            "JOIN genres_movies gm ON g.id = gm.genre_id " +
            "GROUP BY g.genre " +
            "ORDER BY genreCount DESC", nativeQuery = true)
    List<Object[]> findTop10Genres(Pageable pageable);

    @Query("SELECT m FROM Movies m " +
            "JOIN m.genres g " +
            "WHERE g.genre = :genre")
    Page<Movies> findMoviesByGenre(@Param("genre") String genre, Pageable pageable);







}
