package app.crew;

import app.actors.Actors;
import app.movies.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/crew")
public class CrewController {
    CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping
    public Page<Crew> findAll (@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
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
