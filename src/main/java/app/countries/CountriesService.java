package app.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {
    private final CountriesRepository countriesRepository;

    @Autowired
    public CountriesService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    //handle requests from controller
    //findAll is provided by PagingAndSortingRepository Interface
    public Page<Countries> findAll(PageRequest pageRequest) {
        return countriesRepository.findAll(pageRequest);
    }


    public Countries getCountryById(Integer id) {
        return countriesRepository.findById(id).orElse(null);
    }

    public List<Countries> getCountriesByFilmId(Integer filmId) {
        return countriesRepository.findByFilmId(filmId);
    }

    public List<Countries> getCountriesByName(String country) {
        return countriesRepository.findByCountry(country);
    }

    public Countries saveCountry(Countries country) {
        return countriesRepository.save(country);
    }

    public void deleteCountry(Integer id) {
        countriesRepository.deleteById(id);
    }
}