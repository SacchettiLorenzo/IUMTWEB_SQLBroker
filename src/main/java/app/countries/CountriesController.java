package app.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    private final CountriesService countriesService;

    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping
    public List<Countries> getAllCountries() {
        return countriesService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Countries getCountryById(@PathVariable Integer id) {
        return countriesService.getCountryById(id);
    }

    @GetMapping("/film/{filmId}")
    public List<Countries> getCountriesByFilmId(@PathVariable Integer filmId) {
        return countriesService.getCountriesByFilmId(filmId);
    }

    @GetMapping("/name")
    public List<Countries> getCountriesByName(@RequestParam String country) {
        return countriesService.getCountriesByName(country);
    }

    @PostMapping
    public Countries saveCountry(@RequestBody Countries country) {
        return countriesService.saveCountry(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Integer id) {
        countriesService.deleteCountry(id);
    }
}