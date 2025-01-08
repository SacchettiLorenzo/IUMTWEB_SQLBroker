package app.movies;

import app.countries.Countries;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


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

    public List<Movies> getMoviesByDateCountriesGenreLanguageTheme(Integer date, Integer countries_id, Integer genres_id, Integer languages_id, Integer themes_id){
        return moviesRepository.findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId_Left(date, countries_id, genres_id, languages_id, themes_id,null).getContent();
    }

    // Metodo per ottenere tutti i film senza paginazione
    public List<Movies> findAllWithoutPagination() {
        return moviesRepository.findAll();
    }

    public Page<Movies> getMoviesByGenreId(Integer genresId, PageRequest pageRequest) {
        return moviesRepository.findMoviesByGenresId(genresId, pageRequest);
    }

    public ArrayList<Movies> getMoviesByDate(Integer date) {
        return moviesRepository.findByDate(date);
    }

    public List<Movies> getTop10LongestMovies(Integer countryId, Integer genreId, Integer languageId) {
        return this.moviesRepository.findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId_Left(null,countryId, genreId, languageId,null,PageRequest.of(0, 10,Sort.by(Sort.Direction.DESC, "minute"))).getContent();
    }

    public List<Movies> getTop10ShortestMovies(Integer countryId, Integer genreId, Integer languageId) {
        return this.moviesRepository.findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId_Left(null,countryId, genreId, languageId,null,PageRequest.of(0, 10,Sort.by(Sort.Direction.ASC, "minute"))).getContent();
    }
    /*
    public List<Map<Movies, Double>> getTop10BestMovies() {
        return this.moviesRepository.findTop10BestMovies();
    }
     */

    public List<Movies> getTop10BestMoviesByFilters(Integer countryId, Integer genreId, Integer languageId) {
        return this.moviesRepository.findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId_Left(null,countryId, genreId, languageId,null,PageRequest.of(0, 10,Sort.by("rating").descending())).getContent();
    }

    public List<Movies> getTop10WorstMoviesByFilters(Integer countryId, Integer genreId, Integer languageId) {
        return this.moviesRepository.findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId_Left(null,countryId, genreId, languageId,null,PageRequest.of(0, 10,Sort.by(Sort.Direction.ASC, "rating"))).getContent();
    }


    public ArrayList<Movies> getMostPopularMovies(Integer dateBefore, Integer dateAfter, Float rating, Limit limit) {
        return moviesRepository.findMoviesListByDateBetweenAndRatingGreaterThanEqualOrderByRatingDesc(dateBefore, dateAfter, rating, limit);
    }


}
