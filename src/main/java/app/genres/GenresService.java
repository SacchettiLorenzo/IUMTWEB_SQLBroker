package app.genres;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public Optional<Genres> getGenreById(Integer id) {
        return genresRepository.findById(id);
    }

    public List<Object[]> getTop10Genres() {
        return genresRepository.findTop10Genres();
    }

    public Page<Integer> getIdsByGenre(String genre, Pageable pageable) {
        return genresRepository.findIdsByGenre(genre, pageable);
    }


    // Metodo per ottenere tutti i generi con supporto alla paginazione
    public Page<Genres> findAll(Pageable pageable) {
        return genresRepository.findAll(pageable);
    }
}
