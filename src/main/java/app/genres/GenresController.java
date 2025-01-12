package app.genres;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    List<String> classFields = new ArrayList<>();

    /**
     * Constructs a GenresController with the given GenresService.
     * Initializes the list of field names from the Genres class.
     *
     * @param genresService the service used to manage genres
     */
    public GenresController(GenresService genresService) {
        this.genresService = genresService;
        for (Field field : Genres.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * Retrieves a paginated list of genres, sorted by the specified parameter.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of items per page (default is 20)
     * @param sortParam the field to sort by (default is "Id")
     * @param sortDirection the direction of the sort ("ASC" or "DESC", default is "ASC")
     * @return a paginated list of genres
     */
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

    /**
     * Retrieves a genre by its ID.
     *
     * @param id the ID of the genre to retrieve
     * @return the genre with the specified ID, or null if not found
     */
    @GetMapping("/id")
    public Genres getGenreById(@RequestParam Integer id) {
        return genresService.getGenreById(id).orElse(null);
    }

    /**
     * Retrieves a list of genres associated with a specific movie.
     *
     * @param movieId the ID of the movie
     * @return a list of genres associated with the specified movie
     */
    @GetMapping("/movie")
    public ArrayList<Genres> getGenresByMovieId(@RequestParam Integer movieId) {
        return genresService.getGenresByMovieId(movieId);
    }

    /**
     * Retrieves the top 10 most popular genres.
     *
     * @return a list of the top 10 genres with their popularity scores
     */
    @GetMapping("/trending")
    public List<Map<Genres, Object>> getTop10Genres() {
        return genresService.getTop10MostPopularGenres();
    }


}