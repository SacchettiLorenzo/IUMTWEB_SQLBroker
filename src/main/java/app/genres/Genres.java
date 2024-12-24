package app.genres;

import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genres {

    @Id
    private int id;

    @Column(name = "genre", nullable = false, columnDefinition = "TEXT")
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @ManyToMany(mappedBy = "genres")
    private List<Movies> movies;
}
