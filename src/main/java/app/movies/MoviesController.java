package app.movies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Properties;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    MoviesService moviesService;


    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    public Page<Movies> findAll (@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return moviesService.findAll(pageRequest);
    }

    @GetMapping("/trending")
    public Page<Movies> getTopMovies(@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("rating").descending());

        return moviesService.findAll(pageRequest);
    }

    @GetMapping("/id")
    public Movies getMovieById(@RequestParam Integer id) {
        return moviesService.getMovieById(id).orElse(null);
    }

    @GetMapping("/name")
    public ArrayList<Movies> getMovieByName(@RequestParam String partial) {
        ArrayList<Movies> res = new ArrayList<>();
        if(partial != null && !partial.isEmpty()){
            res = moviesService.getMovieByName(partial);
        }

        if(partial != null && !partial.isEmpty()){
            res.addAll(moviesService.getMovieByNameContaining(partial));
        }

        return res;
    }

    @GetMapping("/actor")
    public ArrayList<Movies> getMovieByActorId(@RequestParam Integer id) {
        return moviesService.getMoviesByActorId(id);
    }

    /*
    @GetMapping("/crew")
    public ArrayList<Movies> getMovieByCrewId(@RequestParam Integer id) {

    }

    @GetMapping("/countries")
    public ArrayList<Movies> getMovieByCountryId(@RequestParam Integer id) {

    }

    @GetMapping("/genres")
    public ArrayList<Movies> getMovieByGenreId(@RequestParam Integer id) {

    }

    @GetMapping("/languages")
    public ArrayList<Movies> getMovieByLanguageId(@RequestParam Integer id) {

    }

    @GetMapping("/studios")
    public ArrayList<Movies> getMovieByStudioId(@RequestParam Integer id) {

    }

    @GetMapping("/themes")
    public ArrayList<Movies> getMovieByThemeId(@RequestParam Integer id) {

    }
    */



}

/*
get by id
get by name
get (studio,genres,...) by movie id
get (studio,genres,...) by title containing a given string
get most common value
*/


