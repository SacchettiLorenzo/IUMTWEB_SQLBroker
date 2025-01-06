package app.releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReleasesService {
    private final ReleasesRepository releasesRepository;

    @Autowired
    public ReleasesService(ReleasesRepository releasesRepository) {
        this.releasesRepository = releasesRepository;
    }

    public Page<Releases> findAll(PageRequest pageRequest) {
        return releasesRepository.findAll(pageRequest);
    }

    public Releases getReleaseById(Integer id) {
        return releasesRepository.findById(id).orElse(null);
    }

    public List<Releases> getReleasesByTypeAndCountry(String type, String country) {
        return releasesRepository.findReleasesByTypeAndCountry(type, country);
    }

    public List<Releases> getReleasesByRatingOrderedByDate(String rating) {
        return releasesRepository.findReleasesByRatingOrderedByDate(rating);
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

    public List<Releases> getReleasesByMoviesId(Integer id) {
        releasesRepository.getReleasesByMoviesId(id);
    }
}