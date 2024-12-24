package app.genres;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping("/{id}")
    public Genres getGenreById(@PathVariable Integer id) {
        return genresService.getGenreById(id).orElse(null);
    }

    @GetMapping("/{genre}")
    public List<Integer> getIdsByGenre(@PathVariable String genre) {
        return genresService.getIdsByGenre(genre);
    }

    @GetMapping("/top10")
    public List<Object[]> getTop10Genres() {
        return genresService.getTop10Genres();
    }
}