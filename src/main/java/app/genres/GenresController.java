package app.genres;

import app.movies.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    List<String> classFields = new ArrayList<>();

    @Autowired
    public GenresController(GenresService genresService) {
        this.genresService = genresService;
        for (Field field : Genres.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Genres> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "Id") String sortParam, @RequestParam(required = false, defaultValue = "ASC") String sortDirection){
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
        return genresService.findAll(pageRequest);
    }

    @GetMapping("/id")
    public Genres getGenreById(@RequestParam Integer id) {
        return genresService.getGenreById(id).orElse(null);
    }

    @GetMapping("/movie")
    public ArrayList<Genres> getGenresByMovieId(@RequestParam Integer movieId) {
        return genresService.getGenresByMovieId(movieId);
    }


    @GetMapping("/trending")
    public List<Map<String, Object>> getTop10Genres() {
        return genresService.getTop10MostPopularGenres();
    }


}