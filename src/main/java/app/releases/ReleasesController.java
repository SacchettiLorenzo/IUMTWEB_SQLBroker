package app.releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleasesController {

    private final ReleasesService releasesService;

    @Autowired
    public ReleasesController(ReleasesService releasesService) {
        this.releasesService = releasesService;
    }

    @GetMapping
    public List<Releases> getAllReleases() {
        return releasesService.getAllReleases();
    }

    @GetMapping("/{id}")
    public Releases getReleaseById(@PathVariable Integer id) {
        return releasesService.getReleaseById(id);
    }

    @GetMapping("/film/{filmId}")
    public List<Releases> getReleasesByFilmId(@PathVariable Integer filmId) {
        return releasesService.getReleasesByFilmId(filmId);
    }

    @GetMapping("/country")
    public List<Releases> getReleasesByCountry(@RequestParam String country) {
        return releasesService.getReleasesByCountry(country);
    }

    @PostMapping
    public Releases saveRelease(@RequestBody Releases release) {
        return releasesService.saveRelease(release);
    }

    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Integer id) {
        releasesService.deleteRelease(id);
    }
}