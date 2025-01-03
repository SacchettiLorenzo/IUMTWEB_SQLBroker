package app.studios;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudioService {

    private final StudiosRepository studiosRepository;

    @Autowired
    public StudioService(StudiosRepository studiosRepository) {
        this.studiosRepository = studiosRepository;
    }

    public Page<Studio> findAll(PageRequest pageRequest) {
        return studiosRepository.findAll(pageRequest);
    }

    public Studio getStudioById(Integer id) {
        return studiosRepository.findById(id).orElse(null);
    }

    // Find studios by keyword
    public List<Studio> findStudiosByKeyword(String keyword) {
        return studiosRepository.findStudiosByKeyword(keyword);
    }

    // Count all studios
    public long countAllStudios() {
        return studiosRepository.countAllStudios();
    }

    public List<Studio> findTopStudios(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id"));
        return studiosRepository.findTopStudios(pageable);
    }

    public List<Studio> findStudiosAlphabetically() {
        return studiosRepository.findAllByOrderByStudioAsc();
    }

    public Studio saveStudio(Studio studio) {
        return studiosRepository.save(studio);
    }

    public void deleteStudio(Integer id) {
        studiosRepository.deleteById(id);
    }

}
