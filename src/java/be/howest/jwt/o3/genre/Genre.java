package be.howest.jwt.o3.genre;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Hayk
 */
public class Genre implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    private final long id;
    private String name;

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.name.toLowerCase(Locale.ENGLISH));
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
        final Genre other = (Genre) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
