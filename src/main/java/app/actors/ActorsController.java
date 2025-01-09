package app.actors;

import app.movies.Movies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ObjectName;
import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    ActorsService actorsService;

    List<String> classFields = new ArrayList<>();

    /**
     * Constructor, compile the list of field name of the class
     * @param actorsService
     */
    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
        for (Field field : Actors.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * get a Page of Actors
     * @param page
     * @param size
     * @param sortParam
     * @param sortDirection
     * @return Page<Actors>
     */
    @GetMapping
    public Page<Actors> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "Id") String sortParam, @RequestParam(required = false, defaultValue = "ASC") String sortDirection){
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
        return actorsService.findAll(pageRequest);
    }

    /**
     * get a single actor using id
     * @param id
     * @return Actors
     */
    @GetMapping("/id")
    public Actors getMovieById(@RequestParam Integer id) {
        return actorsService.getActorById(id).orElse(null);
    }

    /**
     * get a list of movie with name containing parameter partial
     * @param partial
     * @return List<Actors>
     */
    @GetMapping("/name")
    public List<Actors> getActorsByPartialName(@RequestParam String partial) {
        if (partial == null || partial.isEmpty()) {
            return new ArrayList<>(); // Restituisce un elenco vuoto se la query è vuota
        }
        List<Actors> partialMatches = actorsService.getActorsByNameContaining(partial);
        return partialMatches;
    }

    /**
     * get all the actors that worked for a movie
     * @param movieId
     * @return ArrayList<Actors>
     */
    @GetMapping("/movie")
    public ArrayList<Actors> getActorsByMovieId(@RequestParam Integer movieId) {
        return actorsService.getActorsByMovieId(movieId);
    }

    @GetMapping("/trending")
    public Page<Map<String, Object>> getTopActors(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return actorsService.findMostPopularActorsList(pageRequest);
    }

    /**
     * get 10 most popular actors with movie count
     * @return List<Map<String, Object>>
     */
    @GetMapping("/top10-mostPopularActors")
    public List<Map<String, Object>> getTop10MostPopularActors() {
        return actorsService.getTop10MostPopularActors();
    }



}
