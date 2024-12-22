package app.movies;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    MoviesService moviesService;


    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    public ArrayList<Movies> getTopMovies(){
        return  this.moviesService.getTop10Movies();
    }

    @GetMapping("/id")
    public Movies getMovieById(@RequestParam Integer id) {
        return moviesService.getMovieById(id).orElse(null);
    }

    @GetMapping("/name")
    public Movies getMovieByName(@RequestParam String name) {
        return moviesService.getMovieByName(name).orElse(null);
    }

    @GetMapping("/actor")
    public ArrayList<Movies> getMovieByActorId(@RequestParam Integer id) {
        return moviesService.getMoviesByActorId(id);
    }

}
