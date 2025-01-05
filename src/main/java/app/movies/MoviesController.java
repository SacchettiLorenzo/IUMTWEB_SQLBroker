package app.movies;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    MoviesService moviesService;

    List<String> classFields = new ArrayList<>();

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
        for (Field field : Movies.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Movies> findAll (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "Id") String sortParam, @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if(sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "Id";
            if(sortDirection != null) {
                if(sortDirection.equalsIgnoreCase("DESC") || sortDirection.equalsIgnoreCase("ASC")) {
                    tempSortDirection = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
                }
            }

            if(classFields.contains(sortParam.toLowerCase())) {
                tempSortParam = sortParam.toLowerCase();
            }
            pageRequest = pageRequest.withSort(Sort.by(tempSortDirection, tempSortParam));
        }
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

    @GetMapping("/filter")
    public ArrayList<Movies> getMoviesByDateCountriesGenreLanguageTheme(@RequestParam(required = false) Integer date,@RequestParam(required = false) Integer countries_id,@RequestParam(required = false) Integer genres_id,@RequestParam(required = false) Integer languages_id,@RequestParam(required = false) Integer themes_id){
        return moviesService.getMoviesByDateCountriesGenreLanguageTheme(date,countries_id,genres_id,languages_id,themes_id);
    }

    /*
    @GetMapping("/similar")
    public ArrayList<Movies> getMoviesByGenreId(@RequestParam Integer id) {
        return moviesService.getMoviesByGenreId(id);
    }
    */

    //todo:
//    @GetMapping("/stats")
//    public ArrayList<Movies> getOverallStats(@RequestParam Integer id) {
//        return moviesService.getMoviesByActorId(id);
//    }

    /*
    @GetMapping("/crew")
    public ArrayList<Movies> getMovieByCrewId(@RequestParam Integer id) {

    }

    @GetMapping("/countries")
    public ArrayList<Movies> getMovieByCountryId(@RequestParam Integer id) {

    }
    */
    @GetMapping("/genres")
    public Page<Movies> getMovieByGenreId(@RequestParam int page, @RequestParam int size,@RequestParam Integer genresId) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("rating").descending());
        return moviesService.getMoviesByGenreId(genresId, pageRequest);
    }
    /*
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


