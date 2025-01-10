package app.themes;

import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "themes")
public class Themes {
    @Id
    private int id;

    @Column(name = "theme", nullable = false, columnDefinition = "TEXT")
    private String theme;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @ManyToMany(mappedBy = "themes")
    private List<Movies> movies;

}

