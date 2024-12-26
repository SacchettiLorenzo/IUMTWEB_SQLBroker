package app.studios;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {
    StudioService studiosService;

    public StudioController(StudioService studiosService) {
        this.studiosService = studiosService;
    }

    @GetMapping
    public List<Studio> getAllStudios() {
        return studiosService.getAllStudios();
    }

    @GetMapping("/{id}")
    public Studio getStudioById(@PathVariable Integer id) {
        return studiosService.getStudioById(id);
    }

    @PostMapping
    public Studio saveStudio(@RequestBody Studio studio) {
        return studiosService.saveStudio(studio);
    }

    @DeleteMapping("/{id}")
    public void deleteStudio(@PathVariable Integer id) {
        studiosService.deleteStudio(id);
    }
}
