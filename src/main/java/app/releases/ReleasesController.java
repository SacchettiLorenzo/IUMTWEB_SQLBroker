package app.releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller to manage release-related operations.
 * Provides endpoints for retrieving, saving, deleting, and filtering release data.
 */
@RestController
@RequestMapping("/releases")
public class ReleasesController {

    private final ReleasesService releasesService;

    List<String> classFields = new ArrayList<>();

    /**
     * Constructor for the controller.
     * Initializes the release service and populates the list of fields in the {@link Releases} class.
     *
     * @param releasesService the service used to manage release data.
     */
    @Autowired
    public ReleasesController(ReleasesService releasesService) {
        this.releasesService = releasesService;
        for (Field field : Releases.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * Retrieves a paginated and sorted list of releases.
     *
     * @param page          the page number to retrieve (default 0).
     * @param size          the number of items per page (default 20).
     * @param sortParam     the field to sort by (default "Id").
     * @param sortDirection the sorting direction ("ASC" or "DESC", default "ASC").
     * @return a page containing the list of releases.
     */
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

    /**
     * Retrieves a release by its ID.
     *
     * @param id the ID of the release.
     * @return the release object.
     */
    @GetMapping("/{id}")
    public Releases getReleaseById(@PathVariable Integer id) {
        return releasesService.getReleaseById(id);
    }

    /**
     * Retrieves releases based on the specified country.
     *
     * @param country the name of the country (default "Unknown").
     * @return a list of releases associated with the specified country.
     */
    @GetMapping("/country")
    public List<Releases> getReleasesByCountry(@RequestParam(required = false, defaultValue = "Unknown") String country) {
        System.out.println("Parameter received in Spring Boot: " + country);
        return releasesService.getReleasesByCountry(country);
    }

    /**
     * Retrieves releases by a specific movie ID.
     *
     * @param movieId the ID of the movie.
     * @return a list of releases associated with the given movie ID.
     */
    @GetMapping("/movie")
    public List<Releases> getReleasesByMoviesId(@RequestParam(required = true) Integer movieId) {
        return releasesService.getReleasesByMoviesId(movieId);
    }

    /**
     * Retrieves releases filtered by type and country.
     *
     * @param type    the type of release (required).
     * @param country the country of release (required).
     * @return a list of releases matching the type and country filters.
     * @throws IllegalArgumentException if the type or country parameters are null.
     */
    @GetMapping("/type")
    public List<Releases> getReleasesByTypeAndCountry(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String country) {
        System.out.println("Parameter 'type' received: " + type);
        System.out.println("Parameter 'country' received: " + country);

        if (type == null || country == null) {
            throw new IllegalArgumentException("Parameters 'type' and 'country' are mandatory.");
        }

        List<Releases> releases = releasesService.getReleasesByTypeAndCountry(type, country);

        releases.forEach(release -> {
            if (release.getRating() == null) {
                release.setRating("N/A");
            }
        });

        return releases;
    }

    /**
     * Retrieves releases filtered by rating, ordered by date.
     *
     * @param rating the rating to filter by.
     * @return a list of releases matching the specified rating.
     */
    @GetMapping("/rating")
    public List<Releases> getReleasesByRatingOrderedByDate(@RequestParam String rating) {
        return releasesService.getReleasesByRatingOrderedByDate(rating);
    }

    /**
     * Saves a new release to the database.
     *
     * @param release the release object to save.
     * @return the saved release.
     */
    @PostMapping
    public Releases saveRelease(@RequestBody Releases release) {
        return releasesService.saveRelease(release);
    }

    /**
     * Deletes a release by its ID.
     *
     * @param id the ID of the release to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Integer id) {
        releasesService.deleteRelease(id);
    }
}