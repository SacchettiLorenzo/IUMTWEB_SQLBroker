package app.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class LanguagesService {
    private final LanguagesRepository languagesRepository;

    @Autowired
    public LanguagesService(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    public ArrayList<Languages> getLanguage(Integer movieId) {
        return languagesRepository.findLanguageByMoviesId(movieId);
    }

    public Optional<String> getTypesByLanguage(Integer languageId) {
        return languagesRepository.findTypeByLanguageId(languageId);
    }

    public ArrayList<Languages> getTop10Languages() {
        return languagesRepository.findTop10LanguagesWithId();
    }

    public Page<Languages> findAll(PageRequest pageRequest) {
        return languagesRepository.findAll(pageRequest);
    }


}
