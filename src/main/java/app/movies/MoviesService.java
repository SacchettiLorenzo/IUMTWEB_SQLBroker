package app.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MoviesService {
    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public Optional<Movies> getMovieById(int id) {
        return moviesRepository.findById(id);
    }

    public Optional<Movies> getMovieByName(String title) {
        return moviesRepository.findByName(title);
    }

    public ArrayList<Movies> getTop10Movies() {
        return moviesRepository.findTop10OrderByRatingDesc();
    }

    public ArrayList<Movies> getMoviesByActorId(int id) {
        return moviesRepository.findMoviesListByActorsId(id);
    }
}
