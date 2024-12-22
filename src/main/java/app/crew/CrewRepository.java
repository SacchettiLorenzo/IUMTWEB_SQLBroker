package app.crew;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Integer> {

    Optional<Crew> findById(Integer integer);

    Optional<Crew> findByName(String name);

    ArrayList<Crew> findCrewListByMoviesId(Integer id);
}
