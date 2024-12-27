package app.countries;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Integer>, PagingAndSortingRepository<Countries, Integer> {
    List<Countries> findByFilmId(Integer filmId);
    List<Countries> findByCountry(String country);
    // Find all unique countries associated with a specific film
    @Query("SELECT DISTINCT c.country FROM Countries c WHERE c.filmId = :filmId")
    List<String> findUniqueCountriesByFilmId(@Param("filmId") Integer filmId);

    // Count the number of films associated with a specific country
    @Query("SELECT COUNT(c) FROM Countries c WHERE c.country = :country")
    long countFilmsByCountry(@Param("country") String country);

    // Find all film IDs originating from a specific country
    @Query("SELECT c.filmId FROM Countries c WHERE c.country = :country")
    List<Integer> findFilmIdsByCountry(@Param("country") String country);

    // Find the top N countries associated with films, ordered alphabetically
    @Query("SELECT DISTINCT c.country FROM Countries c ORDER BY c.country ASC")
    List<String> findTopCountries(Pageable pageable);

    // Count the total number of unique countries registered
    @Query("SELECT COUNT(DISTINCT c.country) FROM Countries c")
    long countAllUniqueCountries();
}