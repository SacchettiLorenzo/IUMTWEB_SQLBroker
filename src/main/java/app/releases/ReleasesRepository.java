package app.releases;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ReleasesRepository extends JpaRepository<Releases, Integer>, PagingAndSortingRepository<Releases, Integer> {
    // Find releases by country
    List<Releases> findByCountry(String country);

    // Find releases of a specific type in a specific country
    @Query("SELECT r FROM Releases r WHERE r.type = :type AND r.country = :country")
    List<Releases> findReleasesByTypeAndCountry(@Param("type") String type, @Param("country") String country);

    // Find releases with a specific rating, ordered by date descending
    @Query("SELECT r FROM Releases r WHERE r.rating = :rating ORDER BY r.date DESC")
    List<Releases> findReleasesByRatingOrderedByDate(@Param("rating") String rating);

    // Find top N releases ordered by date
    @Query("SELECT r FROM Releases r ORDER BY r.date DESC")
    List<Releases> findTopReleases(Pageable pageable);

    @Query ("SELECT DISTINCT r.country FROM Releases r")
    List<String> findDistinctCountry();

    List<Releases> getReleasesByMoviesId(Integer movieId);
}