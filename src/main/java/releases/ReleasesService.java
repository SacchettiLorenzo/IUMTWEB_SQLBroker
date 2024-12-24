package releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleasesService {
    private final ReleasesRepository releasesRepository;

    @Autowired
    public ReleasesService(ReleasesRepository releasesRepository) {
        this.releasesRepository = releasesRepository;
    }

    public List<Releases> getAllReleases() {
        return releasesRepository.findAll();
    }

    public Releases getReleaseById(Integer id) {
        return releasesRepository.findById(id).orElse(null);
    }

    public List<Releases> getReleasesByFilmId(Integer filmId) {
        return releasesRepository.findByFilmId(filmId);
    }

    public List<Releases> getReleasesByCountry(String country) {
        return releasesRepository.findByCountry(country);
    }

    public Releases saveRelease(Releases release) {
        return releasesRepository.save(release);
    }

    public void deleteRelease(Integer id) {
        releasesRepository.deleteById(id);
    }
}