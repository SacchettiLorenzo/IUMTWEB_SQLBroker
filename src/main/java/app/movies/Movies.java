package app.movies;

import app.actors.Actors;
import app.crew.Crew;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "movies")
public class Movies {

    @Id
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "date", nullable = true, columnDefinition = "IntegerEGER")
    private Integer date;

    @Column(name = "tagline", nullable = true, columnDefinition = "TEXT")
    private String tagline;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "minute", nullable = true, columnDefinition = "IntegerEGER")
    private Integer minute;

    @Column(name = "rating", nullable = true, columnDefinition = "FLOAT")
    private Float rating;

    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "actors_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actors> actors;

    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "actors_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Crew> crew;

    /*
    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "countries_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Countries> countries;

    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "genres_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genres> genres;

    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "languages_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "langiage_id")
    )
    private List<Languages> languages;

    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "studios_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private List<Studios> studios;

    //do not create getter and setters for actors in order to avoid nested calls
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name  = "themes_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private List<Themes> themes;
    */


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}
