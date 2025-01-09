package app.movies;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.*;
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

    /**
     * Constructor, compile the list of field name of the class
     * @param moviesService
     */
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
        for (Field field : Movies.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * get a Page of movies
     * @param page
     * @param size
     * @param sortParam
     * @param sortDirection
     * @return Page<Movie>
     */
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

    /**
     * get a single movie using id
     * @param id
     * @return Movies
     */
    @GetMapping("/id")
    public Movies getMovieById(@RequestParam Integer id) {
        return moviesService.getMovieById(id).orElse(null);
    }

    /**
     * get a list of movie with title containing parameter partial
     * @param partial
     * @return ArrayList<Movies>
     */
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

    /**
     * get the movies in which the actor participated
     * @param id id of the actor
     * @return ArrayList<Movies>
     */
    @GetMapping("/actor")
    public ArrayList<Movies> getMovieByActorId(@RequestParam Integer id) {
        return moviesService.getMoviesByActorId(id);
    }

    /**
     * get a list of movies with specific characteristics
     * @param date
     * @param countries_id
     * @param genres_id
     * @param languages_id
     * @param themes_id
     * @return List<Movies>
     */
    @GetMapping("/filter")
    public List<Movies> getMoviesByDateCountriesGenreLanguageTheme(@RequestParam(required = false) Integer date,@RequestParam(required = false) Integer countries_id,@RequestParam(required = false) Integer genres_id,@RequestParam(required = false) Integer languages_id,@RequestParam(required = false) Integer themes_id){
        return moviesService.getMoviesByDateCountriesGenreLanguageTheme(date,countries_id,genres_id,languages_id,themes_id);
    }

    /**
     * get movie by date (year)
     * @param date
     * @return ArrayList<Movies>
     */
    @GetMapping("/date")
    public ArrayList<Movies> getMoviesByDate(@RequestParam(required = true) Integer date){
        return moviesService.getMoviesByDate(date);
    }

    /**
     * get most popular movies within the parameters
     * @param startYear
     * @param endYear
     * @param minRating
     * @param limit
     * @return List<Movies>
     */
    @GetMapping("/popular")
    public List<Movies> getPopularMovies(
            @RequestParam(value = "startYear", defaultValue = "2001") int startYear,
            @RequestParam(value = "endYear", defaultValue = "2024") int endYear,
            @RequestParam(value = "minRating", defaultValue = "4.0") double minRating,
            @RequestParam(value = "limit", defaultValue = "50") int limit
    ) {
        return moviesService.getMostPopularMovies(startYear,endYear, (float) minRating, Limit.of(limit));
    }

    /**
     * get movies by genre
     * @param page
     * @param size
     * @param genresId
     * @return Page<Movies>
     */
    @GetMapping("/genres")
    public Page<Movies> getMovieByGenreId(@RequestParam int page, @RequestParam int size,@RequestParam Integer genresId) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("rating").descending());
        return moviesService.getMoviesByGenreId(genresId, pageRequest);
    }

    /**
     * get top 10 longest movies within parameters
     * @param countryId
     * @param genreId
     * @param languageId
     * @return List<Movies>
     */
    @GetMapping("/top10Longest")
    public List<Movies> getTop10LongestMovies(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Integer languageId) {
        return moviesService.getTop10LongestMovies(countryId, genreId, languageId);
    }

    /**
     * get top 10 shortest movies within parameters
     * @param countryId
     * @param genreId
     * @param languageId
     * @return List<Movies>
     */
    @GetMapping("/top10Shortest")
    public List<Movies> getTop10ShortestMovies(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Integer languageId) {
        return moviesService.getTop10ShortestMovies(countryId, genreId, languageId);
    }

    /**
     * get top 10 Best movies within parameters
     * @param countryId
     * @param genreId
     * @param languageId
     * @return List<Movies>
     */
    @GetMapping("/top10ByIds")
    public List<Movies> getTop10BestMoviesByFilters(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Integer languageId) {

        return moviesService.getTop10BestMoviesByFilters(countryId, genreId, languageId);
    }

    /**
     * get top 10 wors movies within parameters
     * @param countryId
     * @param genreId
     * @param languageId
     * @return List<Movies>
     */
    @GetMapping("/worst10ByIds")
    public List<Movies> getTop10WorstMoviesByFilters(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Integer languageId) {
        return moviesService.getTop10WorstMoviesByFilters(countryId, genreId, languageId);
    }

}




