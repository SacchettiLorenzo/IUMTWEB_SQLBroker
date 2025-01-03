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

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer>, PagingAndSortingRepository<Genres, Integer> {

    Optional<Genres> findById(Integer id);

    ArrayList<Genres> findGenresListByMoviesId(Integer id);

    @Query(value = "SELECT  a.*,count('genre_id') as movie_count " +
            "FROM genres_movies ac join public.genres a on ac.genre_id = a.id group by a.id order by movie_count desc", nativeQuery = true)
    Page<Map<String, Object>> findMostPopularGenresList(Pageable pageable);



}
