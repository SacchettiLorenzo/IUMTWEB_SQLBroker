package app.releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleasesController {

    private final ReleasesService releasesService;

    List<String> classFields = new ArrayList<>();

    @Autowired
    public ReleasesController(ReleasesService releasesService) {
        this.releasesService = releasesService;
        for (Field field : Releases.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Releases> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "Id") String sortParam,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "Id";
            if (sortDirection != null && (sortDirection.equalsIgnoreCase("DESC") || sortDirection.equalsIgnoreCase("ASC"))) {
                tempSortDirection = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
            }

            if (classFields.contains(sortParam.toLowerCase())) {
                tempSortParam = sortParam.toLowerCase();
            }
            pageRequest = pageRequest.withSort(Sort.by(tempSortDirection, tempSortParam));
        }
        return (Page<Releases>) releasesService.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public Releases getReleaseById(@PathVariable Integer id) {
        return releasesService.getReleaseById(id);
    }

    @GetMapping("/country")
    public List<Releases> getReleasesByCountry(@RequestParam String country) {
        return releasesService.getReleasesByCountry(country);
    }

    @GetMapping("/type")
    public List<Releases> getReleasesByTypeAndCountry(
            @RequestParam String type,
            @RequestParam String country
    ) {
        return releasesService.getReleasesByTypeAndCountry(type, country);
    }

    @GetMapping("/rating")
    public List<Releases> getReleasesByRatingOrderedByDate(@RequestParam String rating) {
        return releasesService.getReleasesByRatingOrderedByDate(rating);
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