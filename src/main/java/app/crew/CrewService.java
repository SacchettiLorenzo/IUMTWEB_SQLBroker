package app.crew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrewService {
    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public Optional<Crew> getCrewById(Integer id) {
        return crewRepository.findById(id);
    }

    public Optional<Crew> getCrewByName(String name) {
        return crewRepository.findByName(name);
    }
}
