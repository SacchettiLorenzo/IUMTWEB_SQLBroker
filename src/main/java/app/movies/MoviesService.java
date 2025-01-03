package app.movies;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    //handle requests from controller
    //findAll is provided by PagingAndSortingRepository Interface
    public Page<Movies> findAll(PageRequest pageRequest) {
        return moviesRepository.findAll(pageRequest);
    }

    public Optional<Movies> getMovieById(int id) {
        return moviesRepository.findById(id);
    }

    public ArrayList<Movies> getMovieByName(String title) {
        return moviesRepository.findByName(title);
    }

    public ArrayList<Movies> getMovieByNameContaining(String title) {
        return moviesRepository.findMoviesListByNameIgnoreCaseContaining(title);
    }

    public ArrayList<Movies> getMoviesByActorId(int id) {
        return moviesRepository.findMoviesListByActorsId(id);
    }

    /*
    public ArrayList<Movies> getMoviesByGenreId(int id) {
        return moviesRepository.findMoviesListByGenreIdOrderByDateDesc(Limit.of(10));
    }
    */



}
