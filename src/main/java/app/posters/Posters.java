package app.posters;

import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "posters")
public class Posters {

    @Id
    private int id;

    @Column(name = "link", nullable = false, columnDefinition = "TEXT")
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ManyToMany(mappedBy = "posters")
    private List<Movies> movies;
}
