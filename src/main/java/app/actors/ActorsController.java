package app.actors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    ActorsService actorsService;

    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
    }

    @GetMapping("/name")
    public Actors getActor(@RequestParam String name) {
        return actorsService.getActorByName(name).orElse(null);
    }

    @GetMapping("/movie")
    public ArrayList<Actors> getActorsByMovieId(@RequestParam Integer movieId) {
        return actorsService.getActorsByMovieId(movieId);
    }



}
