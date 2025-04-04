package app.genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public Optional<Genres> getGenreById(Integer id) {
        return genresRepository.findById(id);
    }

    public ArrayList<Genres> getGenresByMovieId(Integer id) {
        return this.genresRepository.findGenresListByMoviesId(id);
    }

    public Page<Genres> findAll(PageRequest pageRequest) {
        return genresRepository.findAll(pageRequest);
    }

    public List<Map<Genres, Object>> getTop10MostPopularGenres() {
        return this.genresRepository.findTop10MostPopularGenres();
    }




}
