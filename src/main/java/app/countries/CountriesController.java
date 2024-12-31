package app.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    private final CountriesService countriesService;

    List<String> classFields = new ArrayList<>();

    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
        for (Field field : Countries.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Countries> findAll (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "Id") String sortParam, @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if(sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "Id";
            if(sortDirection != null) {
                if(sortDirection.equalsIgnoreCase("DESC") || sortDirection.equalsIgnoreCase("ASC")) {
                    tempSortDirection = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
                }
            }

            if(classFields.contains(sortParam.toLowerCase())) {
                tempSortParam = sortParam.toLowerCase();
            }
            pageRequest = pageRequest.withSort(Sort.by(tempSortDirection, tempSortParam));
        }
        return countriesService.findAll(pageRequest);
    }

    @GetMapping("/trending")
    public Page<Countries> getTopCountries(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("country").descending());
        return countriesService.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public Countries getCountryById(@PathVariable Integer id) {
        return countriesService.getCountryById(id);
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