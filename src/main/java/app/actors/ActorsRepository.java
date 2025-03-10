package app.actors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT a FROM Actors a WHERE a.name ILIKE CONCAT('%', :partial, '%')")
    List<Actors> findActorsByNameContaining(@Param("partial") String partial);

    @Query(value = "SELECT a.id, a.name, COUNT(am.actor_id) AS actor_count " +
            "FROM actors_movies am " +
            "JOIN actors a ON am.actor_id = a.id " +
            "GROUP BY a.id, a.name " +
            "ORDER BY actor_count DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Map<String, Object>> findTop10MostPopularActors();
}
