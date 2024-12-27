package app.movies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    public ArrayList<Object> getMovieByName(@RequestParam String partial) {
        ArrayList<Object> res = new ArrayList<>();
        if(partial != null && !partial.isEmpty()){
            //res = moviesService.getMovieByName(partial);
        }

        if(partial != null && !partial.isEmpty()){
            res.addAll(moviesService.getMovieByNameContaining(partial));
        }
        return res;
    }
}
