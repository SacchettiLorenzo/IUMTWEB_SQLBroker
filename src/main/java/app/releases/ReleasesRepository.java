package app.releases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ReleasesRepository extends JpaRepository<Releases, Integer> {
    List<Releases> findByFilmId(Integer filmId);
    List<Releases> findByCountry(String country);
    // Find all releases for a specific film by its ID
    @Query("SELECT r FROM Releases r WHERE r.filmId = :filmId")
    List<Releases> findReleasesByFilmId(@Param("filmId") Integer filmId);

    // Find releases of a specific type (e.g., "Theatrical") in a given country
    @Query("SELECT r FROM Releases r WHERE r.type = :type AND r.country = :country")
    List<Releases> findReleasesByTypeAndCountry(@Param("type") String type, @Param("country") String country);

    // Find releases with a specific rating, ordered by date in descending order
    @Query("SELECT r FROM Releases r WHERE r.rating = :rating ORDER BY r.date DESC")
    List<Releases> findReleasesByRatingOrderedByDate(@Param("rating") String rating);

    // Count the number of releases for a specific type
    @Query("SELECT COUNT(r) FROM Releases r WHERE r.type = :type")
    long countReleasesByType(@Param("type") String type);

    // Find the top N releases ordered by date
    @Query("SELECT r FROM Releases r ORDER BY r.date DESC")
    List<Releases> findTopReleases(Pageable pageable);
}