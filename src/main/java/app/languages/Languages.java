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
