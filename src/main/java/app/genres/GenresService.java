package app.genres;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Integer> getIdsByGenre(String genre) {
        return genresRepository.findIdsByGenre(genre);
    }

    public List<Object[]> getTop10Genres() {
        return genresRepository.findTop10Genres();
    }
}
