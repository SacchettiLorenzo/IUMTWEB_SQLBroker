package app.crew;

import app.movies.Movies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Integer>, PagingAndSortingRepository<Crew, Integer> {

    Optional<Crew> findById(Integer integer);

    Optional<Crew> findByName(String name);

    ArrayList<Crew> findCrewListByMoviesId(Integer id);



    @Query(value = "SELECT c.*,  count('crew_id') as movie_count\n" +
            "FROM crew_movies cm\n" +
            "         join public.crew c on cm.crew_id = c.id\n" +
            "group by c.id\n" +
            "order by movie_count desc;", nativeQuery = true)
    ArrayList<Map<String, Object>> findMostPopularCrewList(Pageable pageable);

    @Query(value = "SELECT distinct c.name, cm.role FROM crew_movies cm\n" +
            "JOIN    crew c on cm.crew_id = c.id\n" +
            "WHERE movie_id = :movieId", nativeQuery = true)
    ArrayList<Map<String, String>> findCrewAndRoleByMoviesId(@Param("movieId") Integer movie_id);
}
