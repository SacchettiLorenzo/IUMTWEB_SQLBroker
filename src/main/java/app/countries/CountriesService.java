package app.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {
    private final CountriesRepository countriesRepository;

    @Autowired
    public CountriesService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
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