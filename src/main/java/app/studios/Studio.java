package app.studios;
import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "studios")
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "studio", nullable = false, columnDefinition = "TEXT")
    private String studio;

    @ManyToMany(mappedBy = "studio")
    private List<Movies> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

}
