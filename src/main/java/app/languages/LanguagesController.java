package app.languages;

import org.springframework.data.domain.Pageable;
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
    public ArrayList<Languages> getLanguage(@RequestParam Integer movieId) {
        return languagesService.getLanguage(movieId);
    }


    @GetMapping("/top10")
    public ArrayList<Languages> getTop10Languages() {
        return languagesService.getTop10Languages();
    }

    @GetMapping("/find-type")
    public Optional<String> getType(@RequestParam Integer languageId) {
        return languagesService.getTypesByLanguage(languageId);
    }



}
