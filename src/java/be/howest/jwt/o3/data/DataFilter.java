package be.howest.jwt.o3.data;

/**
 *
 * @author Hayk
 * @param <T>
 */
public abstract class DataFilter<T> {

    protected String key;
    
    public String getKey() {
        return key;
    }
    
    abstract boolean isValid();
}
