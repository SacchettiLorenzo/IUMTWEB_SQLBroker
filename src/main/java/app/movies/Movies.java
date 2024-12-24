package app.movies;


import app.languages.Languages;
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

    @Column(name = "link", nullable = false, columnDefinition = "TEXT")
    private String link;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "languages_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Languages> languages;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
