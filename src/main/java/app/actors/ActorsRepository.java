package app.actors;

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

import java.util.*;


public interface ActorsRepository extends JpaRepository<Actors,Integer>, PagingAndSortingRepository<Actors, Integer> {

    Optional<Actors> findById(Integer id);
    Optional<Actors> findByName(String name);

    ArrayList<Actors> findActorsListByMoviesId(Integer id);

    @Query(value = "SELECT  a.*,count('actor_id') as movie_count FROM actors_movies ac join public.actors a on ac.actor_id = a.id group by a.id order by movie_count desc", nativeQuery = true)
    Page<Map<String, Object>> findMostPopularActorsList(Pageable pageable);

    @Query("SELECT a FROM Actors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :partial, '%'))")
    List<Actors> findActorsByNameContaining(@Param("partial") String partial);
}
