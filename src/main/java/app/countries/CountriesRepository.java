package app.countries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Integer>, PagingAndSortingRepository<Countries, Integer> {
    // Find countries by ID
    Optional<Countries> findById(Integer id);

    // Find countries by country name
    List<Countries> findByCountry(String country);

    // Find all unique countries (without filmId)
    @Query("SELECT DISTINCT c.country FROM Countries c")
    List<String> findUniqueCountries();

    // Count the number of countries with a specific name
    @Query("SELECT COUNT(c) FROM Countries c WHERE c.country = :country")
    long countCountriesByName(@Param("country") String country);

    // Find all country names ordered alphabetically with pagination
    @Query("SELECT c.country FROM Countries c ORDER BY c.country ASC")
    List<String> findTopCountries(Pageable pageable);

    // Count the total number of unique country names
    @Query("SELECT COUNT(DISTINCT c.country) FROM Countries c")
    long countAllUniqueCountries();

    // Find the top 10 countries ordered by name descending
    @Query("SELECT c FROM Countries c ORDER BY c.country DESC")
    List<Countries> findTop10Countries(Pageable pageable);
}