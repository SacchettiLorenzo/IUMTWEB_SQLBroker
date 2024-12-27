package app.themes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemesController {

    ThemesService themesService;

    public ThemesController(ThemesService themesService) {
        this.themesService = themesService;
    }

    @GetMapping("{id}")
    public Themes getThemeById(@PathVariable Integer id) {
        return themesService.getThemeById(id).orElse(null);
    }

    @GetMapping("{theme}")
    public Optional<Integer> getIdByTheme(@PathVariable String theme) {
        return themesService.getIdByTheme(theme);
    }

    @GetMapping("/top10")
    public List<Object[]> getTop10Themes() {
        return themesService.getTop10Themes();
    }

    @GetMapping
    public Page<Themes> getAllThemes(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return themesService.findAll(pageable);
    }
}
