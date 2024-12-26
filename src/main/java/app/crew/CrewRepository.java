package app.crew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Integer> {

    Optional<Crew> findById(Integer integer);

    Optional<Crew> findByName(String name);

    ArrayList<Crew> findCrewListByMoviesId(Integer id);

    @Query(value = "SELECT c.*,  count('crew_id') as movie_count\n" +
            "FROM crew_movies cm\n" +
            "         join public.crew c on cm.crew_id = c.id\n" +
            "group by c.id\n" +
            "order by movie_count desc LIMIT 100;", nativeQuery = true)
    ArrayList<Map<String, Object>> findMostPopularCrewList();
}
