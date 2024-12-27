package app.crew;

import app.actors.Actors;
import app.movies.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crew")
public class CrewController {
    CrewService crewService;

    List<String> classFields = new ArrayList<>();

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
        for (Field field : Crew.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Crew> findAll (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "Id") String sortParam, @RequestParam(required = false, defaultValue = "ASC") String sortDirection){
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
        return crewService.findAll(pageRequest);
    }

    @GetMapping("/id")
    public Crew getCrewById(@RequestParam Integer id){
        return crewService.getCrewById(id).orElse(null);
    }

    @GetMapping("/name")
    public Crew getCrewByName(@RequestParam String name) {
        return crewService.getCrewByName(name).orElse(null);
    }

    @GetMapping("/movie")
    public ArrayList<Crew> getCrewsByMovieId(@RequestParam Integer movieId) {
        return crewService.getActorsByMovieId(movieId);
    }

    @GetMapping("/trending")
    public ArrayList<Map<String, Object>> getTopActors(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return  crewService.findMostPopularCrewList(pageRequest);
    }
}
