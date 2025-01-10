package app.themes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class ThemesService {

    private final ThemesRepository themesRepository;

    @Autowired
    public ThemesService(ThemesRepository themesRepository) {
        this.themesRepository = themesRepository;
    }

    public ArrayList<Themes> getThemeByMovieId(Integer movieId) {
        return themesRepository.findThemesByMoviesId(movieId);
    }

    public ArrayList<Themes> getTop10Themes() {
        return themesRepository.findTop10ThemesWithId();
    }

    public Page<Themes> findAll(Pageable pageable) {
        return themesRepository.findAll(pageable);
    }
}
