package app.genres;

import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "genre", nullable = false, columnDefinition = "TEXT")
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private List<Movies> movies;

    public Integer getId() {
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


}


/*
    comandi per formulare le query:
    - curl http://localhost:8080/genres (per stampare tutta la tabella generi)
    - curl http://localhost:8080/genres/trending (per trovare i top 10)
    - curl http://localhost:8080/genres/1 (per trovare il genere associato all'id)
    - curl "http://localhost:8080/genres/Comedy/movies?page=0&size=10" (per trovare il film associato al genere)


    tutti i generi
    curl http://localhost:8080/genres?page=0&size=20&sortParam=Id&sortDirection=ASC

    un genere per id
    curl http://localhost:8080/genres/id?id=1

    genere di un film specifico
    curl http://localhost:8080/genres/movie?movieId=1023330
    curl "http://localhost:8080/genres/movie?movieId=1023330"

    generi più popolari
    curl http://localhost:8080/genres/trending?page=0&size=10
    curl "http://localhost:8080/genres/trending?page=0&size=10"



 */
