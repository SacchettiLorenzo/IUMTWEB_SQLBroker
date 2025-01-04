package app.themes;

import app.genres.Genres;
import app.languages.Languages;
import app.languages.LanguagesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemesController {

    ThemesService themesService;
    List<String> classFields = new ArrayList<>();

    public ThemesController(ThemesService themesService) {
        this.themesService = themesService;

        for (Field field : Languages.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

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

    @GetMapping("/theme")
    public ArrayList<Themes> getThemeByMovieId(@RequestParam Integer movieId) {
        return themesService.getThemeByMovieId(movieId);
    }

    //va implementato in movies
    /*
    @GetMapping("/moviesByTheme")
    public List<String> getMoviesByTheme(@RequestParam String theme) {
        return themesService.getMoviesByTheme(theme);
    }
    */

    @GetMapping("/top10")
    public ArrayList<Themes> getTop10Themes() {
        return themesService.getTop10Themes();
    }

}
