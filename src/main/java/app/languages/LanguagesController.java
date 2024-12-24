package app.languages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    LanguagesService languagesService;

    public LanguagesController(LanguagesService languagesService) {
        this.languagesService = languagesService;
    }

    @GetMapping("/type")
    public Languages getTypeLanguages(@RequestParam Integer movie_id) {
        return languagesService.getType(movie_id).orElse(null);
    }

    @GetMapping("/language")
    public Languages getLanguage(@RequestParam Integer movie_id) {
        return languagesService.getLanguage(movie_id).orElse(null);
    }

    @GetMapping("/languages")
    public List<Languages> getLanguages(@RequestParam Integer movie_id) {
        return languagesService.findAllLanguagesById(movie_id);
    }

    @GetMapping("/languages")
    public List<Languages> getLanguagesType(@RequestParam String type) {
        return languagesService.findAllLanguagesByType(type);
    }

    @GetMapping("/movie")
    public List<Languages> getMovieByLanguage(@RequestParam String language) {
        return languagesService.findMovieByLanguage(language);
    }

    // Endpoint per le 10 lingue più usate
    @GetMapping("/top10-languages")
    public List<Object[]> getTop10Languages() {
        return languagesService.getTop10Languages();
    }

    // Endpoint per i 5 tipi più usati
    @GetMapping("/top5-types")
    public List<Object[]> getTop5Types() {
        return languagesService.getTop5Types();
    }


}
