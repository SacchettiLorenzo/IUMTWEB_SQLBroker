package app.studios;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudiosRepository extends JpaRepository<Studio, Integer>, PagingAndSortingRepository<Studio, Integer> {
    Optional<Studio> findById(Integer integer);

    // Find studios by a keyword in the name
    @Query("SELECT s FROM Studio s WHERE LOWER(s.studio) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Studio> findStudiosByKeyword(@Param("keyword") String keyword);

    // Count the total number of studios
    @Query("SELECT COUNT(s) FROM Studio s")
    long countAllStudios();

    // Find the top N studios by ID
    @Query("SELECT s FROM Studio s ORDER BY s.id ASC")
    List<Studio> findTopStudios(Pageable pageable);

    // Find studios alphabetically
    @Query("SELECT s FROM Studio s ORDER BY s.studio ASC")
    List<Studio> findAllByOrderByStudioAsc();

}