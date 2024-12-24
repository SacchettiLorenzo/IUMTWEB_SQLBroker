package app.posters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/posters")
public class PostersController {

    private final PostersService postersService;

    public PostersController(PostersService postersService) {
        this.postersService = postersService;
    }

    @GetMapping("/{id}")
    public Optional<String> getLinkById(@PathVariable int id) {
        return postersService.getLinkById(id);
    }
}
