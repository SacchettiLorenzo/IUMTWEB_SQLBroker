package app.languages;

import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "languages")
public class Languages {
    @Id
    private int id;

    @Column(name = "language", nullable = false, columnDefinition = "TEXT")
    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @ManyToMany(mappedBy = "languages")
    private List<Movies> movies;


}


/*
    comandi per formulare le query:
    - curl http://localhost:8080/languages (restituisce tutta la tabella di languages)
    - curl "http://localhost:8080/languages/English?page=0&size=10" (restituisce tutti i nomi dei film con lingua inglese)
    - curl http://localhost:8080/languages/top10-languages (per sapere le 10 lingue più popolari)
    - curl "http://localhost:8080/languages/language?movie_id=1" (restituisce la lingua in base all'id del film passato)


 */
