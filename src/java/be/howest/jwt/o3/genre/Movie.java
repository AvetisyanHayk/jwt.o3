package be.howest.jwt.o3.genre;

import java.io.Serializable;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class Movie implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final long id;
    private String title;
    private int year;
    private int rating;
    private Set<Genre> genres;

    public Movie(long id, String title, int year, int rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.title.toLowerCase(Locale.ENGLISH));
        hash = 37 * hash + this.year;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.year != other.year) {
            return false;
        }
        return title.equalsIgnoreCase(other.title);
    }
    
    @Override
    public String toString() {
        return new StringBuilder()
                .append("(").append(year).append(") ")
                .append(title)
                .append(" [").append(rating).append("]")
                .toString();
    }
}
