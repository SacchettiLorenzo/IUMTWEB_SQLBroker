package app.posters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostersRepository extends JpaRepository<Posters, Integer> {

    Optional<String> findLinkById(int id);
}
