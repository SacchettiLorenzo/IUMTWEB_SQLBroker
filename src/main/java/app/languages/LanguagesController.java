package app.languages;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    LanguagesService languagesService;
    List<String> classFields = new ArrayList<>();

    /**
     * Constructs a LanguagesController with the given LanguagesService.
     * Initializes the list of field names from the Languages class.
     *
     * @param languagesService the service used to manage languages
     */
    public LanguagesController(LanguagesService languagesService) {
        this.languagesService = languagesService;

        for (Field field : Languages.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * Retrieves a paginated list of languages, sorted by the specified parameter.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of items per page (default is 20)
     * @param sortParam the field to sort by (default is "id")
     * @param sortDirection the direction of the sort ("ASC" or "DESC", default is "ASC")
     * @return a paginated list of languages
     */
    @GetMapping
    public Page<Languages> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size,
                                   @RequestParam(required = false, defaultValue = "id") String sortParam,
                                   @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "id";

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

        return languagesService.findAll(pageRequest);
    }

    /**
     * Retrieves the languages associated with a specific movie ID.
     *
     * @param movieId the ID of the movie
     * @return a list of languages associated with the specified movie
     */
    @GetMapping("/language")
    public ArrayList<Languages> getLanguage(@RequestParam Integer movieId) {
        return languagesService.getLanguage(movieId);
    }

    /**
     * Retrieves the top 10 most popular languages.
     *
     * @return a list of the top 10 languages
     */
    @GetMapping("/top10")
    public ArrayList<Languages> getTop10Languages() {
        return languagesService.getTop10Languages();
    }

    /**
     * Retrieves the type of a language by its ID.
     *
     * @param languageId the ID of the language
     * @return an optional containing the type of the language, or empty if not found
     */
    @GetMapping("/find-type")
    public Optional<String> getType(@RequestParam Integer languageId) {
        return languagesService.getTypesByLanguage(languageId);
    }



}
