package app.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public Optional<Languages> getType(Integer id){
        return this.languagesRepository.findTypeLanguagesById(id);
    }

    public Optional<Languages> getLanguage(Integer id){
        return this.languagesRepository.findLanguageById(id);
    }

    public List<Languages> findAllLanguagesById(Integer id){
        return this.languagesRepository.findAllLanguagesById(id);
    }

    public List<Languages> findAllLanguagesByType(String type){
        return this.languagesRepository.findAllLanguagesByType(type);
    }

    public List<Languages> findMovieByLanguage(String language){
        return this.languagesRepository.findMovieByLanguage(language);
    }

    public List<Object[]> getTop10Languages() {
        return languagesRepository.findTop10Languages();
    }

    public List<Object[]> getTop5Types() {
        return languagesRepository.findTop5Types();
    }



}
