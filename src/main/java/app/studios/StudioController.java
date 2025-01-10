package app.studios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing studio-related operations.
 * Provides endpoints to retrieve, search, save, and delete studio data.
 */
@RestController
@RequestMapping("/studio")
public class StudioController {

    private final StudioService studiosService;

    private final List<String> classFields = new ArrayList<>();

    /**
     * Constructor for the controller.
     * Initializes the studio service and populates the list of fields in the {@link Studio} class.
     *
     * @param studiosService the service used to manage studio data.
     */
    @Autowired
    public StudioController(StudioService studiosService) {
        this.studiosService = studiosService;
        for (Field field : Studio.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * Retrieves a paginated and sorted list of studios.
     *
     * @param page          the page number to retrieve (default 0).
     * @param size          the number of items per page (default 20).
     * @param sortParam     the field to sort by (default "Id").
     * @param sortDirection the sorting direction ("ASC" or "DESC", default "ASC").
     * @return a page containing the list of studios.
     */
    @GetMapping
    public Page<Studio> findAll(
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
        return studiosService.findAll(pageRequest);
    }

    /**
     * Retrieves a specific studio by its ID.
     *
     * @param id the ID of the studio.
     * @return the studio object.
     */
    @GetMapping("/{id}")
    public Studio getStudioById(@PathVariable Integer id) {
        return studiosService.getStudioById(id);
    }

    /**
     * Searches studios by a specific keyword.
     *
     * @param keyword the search keyword.
     * @return a list of studios matching the search criteria.
     */
    @GetMapping("/search")
    public List<Studio> findStudiosByKeyword(@RequestParam String keyword) {
        return studiosService.findStudiosByKeyword(keyword);
    }

    /**
     * Retrieves the total count of all studios.
     *
     * @return the total number of studios.
     */
    @GetMapping("/count")
    public long countAllStudios() {
        return studiosService.countAllStudios();
    }

    /**
     * Retrieves the top studios based on a specified limit.
     *
     * @param limit the maximum number of top studios to return (default 10).
     * @return a list of top studios.
     */
    @GetMapping("/top")
    public List<Studio> findTopStudios(@RequestParam(defaultValue = "10") int limit) {
        return studiosService.findTopStudios(limit);
    }

    /**
     * Retrieves a list of studios ordered alphabetically.
     *
     * @return a list of studios sorted in alphabetical order.
     */
    @GetMapping("/alphabetical")
    public List<Studio> findStudiosAlphabetically() {
        return studiosService.findStudiosAlphabetically();
    }

    /**
     * Saves a new studio to the database.
     *
     * @param studio the studio object to save.
     * @return the saved studio.
     */
    @PostMapping
    public Studio saveStudio(@RequestBody Studio studio) {
        return studiosService.saveStudio(studio);
    }

    /**
     * Deletes a studio by its ID.
     *
     * @param id the ID of the studio to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteStudio(@PathVariable Integer id) {
        studiosService.deleteStudio(id);
    }
}