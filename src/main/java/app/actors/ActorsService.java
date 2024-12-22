package app.actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorsService {
    private final ActorsRepository actorsRepository;

    @Autowired
    public ActorsService(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
    }

    public Optional<Actors> getActor(Integer id) {
        return this.actorsRepository.findById(id);
    }
    public Optional<Actors> getActorByName(String name) {
        return this.actorsRepository.findByName(name);
    }

    public ArrayList<Actors> getActorsByMovieId(Integer id) {
        return this.actorsRepository.findActorsListByMoviesId(id);
    }
}
