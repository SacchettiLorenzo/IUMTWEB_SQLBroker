package app.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller to manage country-related operations.
 * Provides endpoints for retrieving, saving, deleting, and filtering country data.
 */
@RestController
@RequestMapping("/countries")
public class CountriesController {

    private final CountriesService countriesService;

    List<String> classFields = new ArrayList<>();

    /**
     * Constructor for the controller.
     * Initializes the country service and populates the list of fields in the {@link Countries} class.
     *
     * @param countriesService the service used to manage country data.
     */
    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
        for (Field field : Countries.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * Retrieves a paginated and sorted list of countries.
     *
     * @param page          the page number to retrieve (default 0).
     * @param size          the number of items per page (default 20).
     * @param sortParam     the field to sort by (default "Id").
     * @param sortDirection the sorting direction ("ASC" or "DESC", default "ASC").
     * @return a page containing the list of countries.
     */
    @GetMapping
    public Page<Countries> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "Id") String sortParam,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {

        PageRequest pageRequest = PageRequest.of(page, size);
        if (sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "Id";
            if (sortDirection != null) {
                if (sortDirection.equalsIgnoreCase("DESC") || sortDirection.equalsIgnoreCase("ASC")) {
                    tempSortDirection = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
                }
            }

            if (classFields.contains(sortParam.toLowerCase())) {
                tempSortParam = sortParam.toLowerCase();
            }
            pageRequest = pageRequest.withSort(Sort.by(tempSortDirection, tempSortParam));
        }
        return countriesService.findAll(pageRequest);
    }

    /**
     * Retrieves a country based on the associated movie ID.
     *
     * @param movieId the ID of the movie.
     * @return a list of countries associated with the specified movie ID.
     */
    @GetMapping("/id")
    public ArrayList<Countries> getCountryById(@RequestParam Integer movieId) {
        return countriesService.getCountryById(movieId);
    }

    /**
     * Retrieves a list of countries based on the given name.
     *
     * @param country the name (or partial name) of the country to search for.
     * @return a list of countries matching the search criteria.
     */
    @GetMapping("/name")
    public List<Countries> getCountriesByName(@RequestParam String country) {
        return countriesService.getCountriesByName(country);
    }

    /**
     * Retrieves a list of the top 10 most popular countries based on movie counts.
     *
     * @return a list containing the data for the top 10 countries.
     */
    @GetMapping("/trending")
    public List<Map<String, Object>> getTop10Countries() {
        return countriesService.getTop10MostPopularCountries();
    }
}