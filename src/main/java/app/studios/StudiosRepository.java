package app.studios;

import app.movies.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudiosRepository extends JpaRepository<Studio, Integer>, PagingAndSortingRepository<Studio, Integer> {
    Optional<Studio> findById(Integer integer);

    Optional<Studio> findByName(String name);

    ArrayList<Studio> findStudioListByMoviesId(Integer id);

    // Find all studios whose name contains a specific keyword (case insensitive)
    @Query("SELECT s FROM Studio s WHERE LOWER(s.studio) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Studio> findStudiosByKeyword(@Param("keyword") String keyword);

    // Find the first N studios ordered by ID in ascending order
    @Query("SELECT s FROM Studio s ORDER BY s.id ASC")
    List<Studio> findTopStudios(Pageable pageable);

    // Count the total number of studios
    @Query("SELECT COUNT(s) FROM Studio s")
    long countAllStudios();

}