package app.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguagesService {
    private final LanguagesRepository languagesRepository;

    @Autowired
    public LanguagesService(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    public Optional<String> getLanguage(Integer movieId) {
        return languagesRepository.findLanguageByMovieId(movieId);
    }

    public Page<String> getMoviesByLanguage(String language, Pageable pageable) {
        return languagesRepository.findMoviesByLanguage(language, pageable);
    }

    public List<Object[]> getTop10Languages() {
        return languagesRepository.findTop10Languages();
    }

    public Page<Languages> findAll(PageRequest pageRequest) {
        return languagesRepository.findAll(pageRequest);
    }


}
