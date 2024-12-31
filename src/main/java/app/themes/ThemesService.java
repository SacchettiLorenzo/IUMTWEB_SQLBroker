package app.themes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemesService {

    private final ThemesRepository themesRepository;

    @Autowired
    public ThemesService(ThemesRepository themesRepository) {
        this.themesRepository = themesRepository;
    }

    public Optional<String> getThemeByMovieId(Integer movieId) {
        return themesRepository.getThemeByMovieId(movieId);
    }

    public List<String> getMoviesByTheme(String theme) {
        return themesRepository.getMoviesByTheme(theme);
    }

    public List<Object[]> getTop10Themes() {
        return themesRepository.findTop10Themes().stream().limit(10).collect(Collectors.toList());
    }

    public Page<Themes> findAll(Pageable pageable) {
        return themesRepository.findAll(pageable);
    }
}
