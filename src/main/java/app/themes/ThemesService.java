package app.themes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ThemesService {

    private final ThemesRepository themesRepository;

    @Autowired
    public ThemesService(ThemesRepository themesRepository) {
        this.themesRepository = themesRepository;
    }

    public Optional<Themes> getThemeById(Integer id) {
        return themesRepository.findById(id);
    }

    public Optional<Integer> getIdByTheme(String theme) {
        return themesRepository.findIdByTheme(theme);
    }

    public List<Object[]> getTop10Themes() {
        return themesRepository.findTop10Themes();
    }

    public Page<Themes> findAll(Pageable pageable) {
        return themesRepository.findAll(pageable);
    }
}
