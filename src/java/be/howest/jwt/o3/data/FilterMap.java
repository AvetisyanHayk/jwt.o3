package be.howest.jwt.o3.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Hayk
 */
public class FilterMap {

    private final Map<String, DataFilter> filters = new LinkedHashMap<>();

    public void addFilter(DataFilter filter) {
        if (filter.isValid()) {
            filters.put(filter.getKey(), filter);
        }
    }
    
    private String firstKey() {
        if (filters.isEmpty()) {
            return "";
        }
        return filters.keySet().toArray(new String[filters.size()])[0];
    }
    
    @Override
    public String toString() {
        if (filters.isEmpty()) {
            return "";
        }
        return " WHERE " + filters.entrySet().stream().map(Entry::getValue).map(DataFilter::toString)
                .reduce("", (previous, current)
                        -> (("".equals(previous)) ? previous : previous + " AND ") + current);
    }
}
