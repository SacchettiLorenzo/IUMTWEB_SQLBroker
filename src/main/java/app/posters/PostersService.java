package app.posters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostersService {

    private final PostersRepository postersRepository;

    @Autowired
    public PostersService(PostersRepository postersRepository) {
        this.postersRepository = postersRepository;
    }

    public Optional<String> getLinkById(int id) {
        return postersRepository.findLinkById(id);
    }
}
