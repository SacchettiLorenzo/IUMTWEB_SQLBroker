package app.releases;

import app.movies.Movies;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "releases")
public class Releases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id", nullable = false)
    private Integer filmId;

    @Column(name = "country", nullable = false, columnDefinition = "TEXT")
    private String country;

    @Column(name = "date", nullable = true)
    private String date; // You can use LocalDate if the date format is standard

    @Column(name = "type", nullable = true, columnDefinition = "TEXT")
    private String type;

    @Column(name = "rating", nullable = true, columnDefinition = "TEXT")
    private String rating;

    @ManyToMany(mappedBy = "releases")
    private List<Movies> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}