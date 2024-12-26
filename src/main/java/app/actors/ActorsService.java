package app.actors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Actors> getActorById(Integer id) {
        return this.actorsRepository.findById(id);
    }
    public Optional<Actors> getActorByName(String name) {
        return this.actorsRepository.findByName(name);
    }

    public ArrayList<Actors> getActorsByMovieId(Integer id) {
        return this.actorsRepository.findActorsListByMoviesId(id);
    }

    public ArrayList<Map<String, Object>> findMostPopularActorsList() {
        return this.actorsRepository.findMostPopularActorsList();
    }
}
