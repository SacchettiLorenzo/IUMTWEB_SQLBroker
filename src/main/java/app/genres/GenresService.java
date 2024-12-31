package app.genres;

import app.movies.Movies;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

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

    public List<Object[]> getTop10Genres(Pageable pageable) {
        return genresRepository.findTop10Genres(pageable);
    }

    public Page<Genres> findAll(Pageable pageable) {
        return genresRepository.findAll(pageable);
    }

    public Page<Movies> getMoviesByGenre(String genre, Pageable pageable) {
        return genresRepository.findMoviesByGenre(genre, pageable);
    }









}
