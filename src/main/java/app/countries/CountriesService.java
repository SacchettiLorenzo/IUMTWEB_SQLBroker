package app.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public ArrayList<Countries> getCountryById(Integer movieId) {
        return countriesRepository.findCountriesByMoviesId(movieId);
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

    public List<Map<String, Object>> getTop10MostPopularCountries() {
        return this.countriesRepository.findTop10MostPopularCountries();
    }
}