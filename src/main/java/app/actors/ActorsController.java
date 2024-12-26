package app.actors;

import app.movies.Movies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    ActorsService actorsService;

    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
    }

    @GetMapping("/id")
    public Actors getMovieById(@RequestParam Integer id) {
        return actorsService.getActorById(id).orElse(null);
    }

    @GetMapping("/name")
    public Actors getActor(@RequestParam String name) {
        return actorsService.getActorByName(name).orElse(null);
    }

    @GetMapping("/movie")
    public ArrayList<Actors> getActorsByMovieId(@RequestParam Integer movieId) {
        return actorsService.getActorsByMovieId(movieId);
    }

    @GetMapping("/trending")
    public ArrayList<Map<String, Object>> getTopActors() {
        return actorsService.findMostPopularActorsList();
    }



}
