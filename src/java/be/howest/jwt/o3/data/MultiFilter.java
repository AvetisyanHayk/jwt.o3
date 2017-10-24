package be.howest.jwt.o3.data;

import java.util.Set;

/**
 *
 * @author Hayk
 * @param <T>
 */
public class MultiFilter<T> extends DataFilter<T> {

    private final Set<T> values;

    public MultiFilter(String key, Set<T> values) {
        this.key = key;
        this.values = values;
    }
    
    @Override
    public String getKey() {
        return key;
    }

    private String getValuesToString() {
        if (values.isEmpty()) {
            return "";
        }
        return "('" + values.stream().map(T::toString)
                .reduce("", (previous, current)
                        -> (("".equals(previous)) ? previous : previous + "', '") + current) + "')";
    }

    @Override
    public String toString() {
        if (key == null || values == null || values.isEmpty()) {
            return "";
        }
        return new StringBuilder()
                .append(key).append(" IN ")
                .append(getValuesToString())
                .toString();
    }

    @Override
    public boolean isValid() {
        return key != null && values != null && !values.isEmpty();
    }

}
