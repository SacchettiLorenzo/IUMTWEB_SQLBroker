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


/*
    comandi per formulare le query:
    - curl http://localhost:8080/themes   (per ottenere tutti i temi)
    - curl http://localhost:8080/themes/top10  (ottieni i 10 temi più popolari)
    - curl "http://localhost:8080/themes/moviesByTheme?theme=Humanity and the world around us" (ti ritorna tutti i film con quel thema)
    - curl "http://localhost:8080/themes/themeByMovieId?movieId=1000001" (ottieni il thema specifico in base all'id del film)


 */

