package app.themes;

import app.languages.Languages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemesController {

    ThemesService themesService;
    List<String> classFields = new ArrayList<>();

    /**
     * Constructs a ThemesController with the given ThemesService.
     * Initializes the list of field names from the Themes class.
     *
     * @param themesService the service used to manage themes
     */
    public ThemesController(ThemesService themesService) {
        this.themesService = themesService;

        for (Field field : Languages.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    /**
     * Retrieves a paginated list of themes, sorted by the specified parameter.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of items per page (default is 20)
     * @param sortParam the field to sort by (default is "Id")
     * @param sortDirection the direction of the sort ("ASC" or "DESC", default is "ASC")
     * @return a paginated list of themes
     */
    @GetMapping
    public Page<Themes> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "Id") String sortParam, @RequestParam(required = false, defaultValue = "ASC") String sortDirection){
        PageRequest pageRequest = PageRequest.of(page, size);
        if(sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "Id";
            if(sortDirection != null) {
                if(sortDirection.equalsIgnoreCase("DESC") || sortDirection.equalsIgnoreCase("ASC")) {
                    tempSortDirection = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
                }
            }

            if(classFields.contains(sortParam.toLowerCase())) {
                tempSortParam = sortParam.toLowerCase();
            }
            pageRequest = pageRequest.withSort(Sort.by(tempSortDirection, tempSortParam));
        }
        return themesService.findAll(pageRequest);
    }

    /**
     * Retrieves the themes associated with a specific movie ID.
     *
     * @param movieId the ID of the movie
     * @return a list of themes associated with the specified movie
     */
    @GetMapping("/theme")
    public ArrayList<Themes> getThemeByMovieId(@RequestParam Integer movieId) {
        return themesService.getThemeByMovieId(movieId);
    }

    /**
     * Retrieves the top 10 most popular themes.
     *
     * @return a list of the top 10 themes
     */
    @GetMapping("/top10")
    public ArrayList<Themes> getTop10Themes() {
        return themesService.getTop10Themes();
    }

}
