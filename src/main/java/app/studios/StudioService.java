package app.studios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Studio saveStudio(Studio studio) {
        return studiosRepository.save(studio);
    }

    public void deleteStudio(Integer id) {
        studiosRepository.deleteById(id);
    }

}
