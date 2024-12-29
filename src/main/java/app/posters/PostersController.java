package app.posters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posters")
public class PostersController {

    PostersService postersService;

    public PostersController(PostersService postersService) {
        this.postersService = postersService;
    }

    @GetMapping("/id")
    public Optional<String> getLinkById(@PathVariable int id) {
        return postersService.getLinkById(id);
    }

    @GetMapping
    public Page<Posters> getAllPosters(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postersService.findAll(pageable);
    }
}
