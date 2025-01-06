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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    ActorsService actorsService;

    List<String> classFields = new ArrayList<>();

    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
        for (Field field : Actors.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

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
    public Page<Map<String, Object>> getTopActors(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return actorsService.findMostPopularActorsList(pageRequest);
    }

    @GetMapping("/top10-mostPopularActors")
    public List<Map<String, Object>> getTop10MostPopularActors() {
        return actorsService.getTop10MostPopularActors();
    }



}
