package app.movies;


import app.genres.Genres;
import app.languages.Languages;
import app.themes.Themes;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movies {
    @Id
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "tagline", nullable = false, columnDefinition = "TEXT")
    private String tagline;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "minute", nullable = false, columnDefinition = "INTEGER")
    private int minute;

    @Column(name = "rating", nullable = false, columnDefinition = "FLOAT")
    private float rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "languages_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Languages> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "themes_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private List<Themes> themes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genres_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genres> genres;

    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "posters_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "posters_id")
    )
    private List<Posters> posters;
    */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
