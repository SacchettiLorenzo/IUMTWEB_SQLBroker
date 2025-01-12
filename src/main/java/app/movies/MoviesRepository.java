package app.movies;

import app.crew.Crew;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer>, PagingAndSortingRepository<Movies, Integer> {

    Optional<Movies> findById(int id);

    ArrayList<Movies> findByName(String title);

    ArrayList<Movies> findMoviesListByNameIgnoreCaseContaining(String title);

    ArrayList<Movies> findMoviesByMinuteIsBetween(int min, int max);
    ArrayList<Movies> findMoviesByRatingBetween(float min, float max);

    ArrayList<Movies> findAllByOrderByRatingDesc();

    ArrayList<Movies> findMoviesListByActorsId(int actorsId);

    //ArrayList<Movies> findMoviesListByGenreIdOrderByDateDesc(Limit limit);

    Page<Movies> findMoviesByGenresId(Integer genresId, Pageable pageable);

    //tried with example matcher but cant figure out how to handle null params
    ArrayList<Movies> findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId(
            @Param("date") Integer date,
            @Param("countries_id") Integer countries_id,
            @Param("genres_id") Integer genres_id,
            @Param("languages_id") Integer languages_id,
            @Param("themes_id") Integer themes_id);


    @Query(value = "select DISTINCT(m) from Movies m " +
            "left join m.countries countries " +
            "left join m.genres genres " +
            "left join m.languages languages " +
            "left join m.themes themes " +
            "where (:date is null or m.date = :date) " +
            "and (:countries_id is null or countries.id = :countries_id) " +
            "and (:genres_id is null or genres.id = :genres_id) " +
            "and (:languages_id is null or languages.id = :languages_id) " +
            "and (:themes_id is null or themes.id = :themes_id)")
    Page<Movies> findMoviesListByDateAndCountriesIdAndGenresIdAndLanguagesIdAndThemesId_Left(
            @Param("date") Integer date,
            @Param("countries_id") Integer countries_id,
            @Param("genres_id") Integer genres_id,
            @Param("languages_id") Integer languages_id,
            @Param("themes_id") Integer themes_id,
            Pageable pageable);

    ArrayList<Movies> findByDate(Integer date);

    ArrayList<Movies> findMoviesListByDateBetweenAndRatingGreaterThanEqualOrderByRatingDesc(Integer dateAfter, Integer dateBefore, Float ratingIsGreaterThan, Limit limit);




}
