package app.languages;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    LanguagesService languagesService;
    List<String> classFields = new ArrayList<>();

    public LanguagesController(LanguagesService languagesService) {
        this.languagesService = languagesService;

        for (Field field : Languages.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Languages> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size,
                                   @RequestParam(required = false, defaultValue = "id") String sortParam,
                                   @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size);

        // Validazione del parametro di ordinamento
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

    @GetMapping("/language")
    public Languages getLanguage(@RequestParam Integer movie_id) {
        return languagesService.getLanguage(movie_id).orElse(null);
    }

    @GetMapping("/languages")
    public Page<Languages> getLanguagesByMovieId(@RequestParam Integer movie_id,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "20") int size,
                                                 @RequestParam(required = false, defaultValue = "id") String sortParam,
                                                 @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(
                sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortParam));

        return languagesService.findAllLanguagesById(movie_id, pageable);
    }


    @GetMapping("/movies/language")
    public Page<Languages> getMoviesByLanguage(@RequestParam String language,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "20") int size,
                                               @RequestParam(required = false, defaultValue = "id") String sortParam,
                                               @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(
                sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortParam));

        return languagesService.findMovieByLanguage(language, pageable);
    }

    @GetMapping("/top10-languages")
    public List<Object[]> getTop10Languages() {
        return languagesService.getTop10Languages();
    }



}
