package app.studios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {
    StudioService studiosService;

    List<String> classFields = new ArrayList<>();

    @Autowired
    public StudioController(StudioService studiosService) {
        this.studiosService = studiosService;
        for (Field field : Studio.class.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
        }
    }

    @GetMapping
    public Page<Studio> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "Id") String sortParam,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (sortParam != null) {
            Sort.Direction tempSortDirection = Sort.Direction.ASC;
            String tempSortParam = "Id";
            if (sortDirection != null && (sortDirection.equalsIgnoreCase("DESC") || sortDirection.equalsIgnoreCase("ASC"))) {
                tempSortDirection = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
            }

            if (classFields.contains(sortParam.toLowerCase())) {
                tempSortParam = sortParam.toLowerCase();
            }
            pageRequest = pageRequest.withSort(Sort.by(tempSortDirection, tempSortParam));
        }
        return studiosService.findAll(pageRequest);
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
