package app.genres;

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

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping("/id")
    public Genres getGenreById(@PathVariable Integer id) {
        return genresService.getGenreById(id).orElse(null);
    }

    @GetMapping("/ids")
    public Page<Integer> getIdsByGenre(@RequestParam String genre, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return genresService.getIdsByGenre(genre, pageable);
    }

    @GetMapping("/top10")
    public List<Object[]> getTop10Genres() {
        return genresService.getTop10Genres();
    }

    @GetMapping
    public Page<Genres> findAll(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return genresService.findAll(pageRequest);
    }
}