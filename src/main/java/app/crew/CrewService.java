package app.crew;

import app.actors.Actors;
import app.movies.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class CrewService {
    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public Page<Crew> findAll(PageRequest pageRequest) {
        return crewRepository.findAll(pageRequest);
    }

    public Optional<Crew> getCrewById(Integer id) {
        return crewRepository.findById(id);
    }

    public Optional<Crew> getCrewByName(String name) {
        return crewRepository.findByName(name);
    }

    public ArrayList<Crew> getActorsByMovieId(Integer id) {
        return this.crewRepository.findCrewListByMoviesId(id);
    }

    public ArrayList<Map<String, Object>> findMostPopularCrewList(PageRequest pageRequest) {
        return this.crewRepository.findMostPopularCrewList(pageRequest);
    }
}
