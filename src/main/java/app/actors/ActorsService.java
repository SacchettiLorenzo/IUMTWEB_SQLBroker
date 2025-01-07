package app.actors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.*;

@Service
public class ActorsService {
    private final ActorsRepository actorsRepository;

    @Autowired
    public ActorsService(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
    }

    public Page<Actors> findAll(PageRequest pageRequest) {
        return actorsRepository.findAll(pageRequest);
    }

    public Optional<Actors> getActorById(Integer id) {
        return this.actorsRepository.findById(id);
    }
    public Optional<Actors> getActorByName(String name) {
        return this.actorsRepository.findByName(name);
    }

    public ArrayList<Actors> getActorsByMovieId(Integer id) {
        return this.actorsRepository.findActorsListByMoviesId(id);
    }

    public Page<Map<String, Object>> findMostPopularActorsList(Pageable pageable) {
        return this.actorsRepository.findMostPopularActorsList(pageable);
    }

    public List<Actors> getActorsByNameContaining(String partial) {
        return actorsRepository.findActorsByNameContaining(partial);
    }

    public List<Map<String, Object>> getTop10MostPopularActors() {
        return this.actorsRepository.findTop10MostPopularActors();
    }
}
