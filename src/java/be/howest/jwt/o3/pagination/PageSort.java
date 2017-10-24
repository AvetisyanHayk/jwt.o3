package be.howest.jwt.o3.pagination;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class PageSort {
    private final Map<String, Set<Column>> sortOrderMap = new LinkedHashMap<>();
    
    public void add(String key, Column column) {
        if (key != null) {
            Set<Column> columns = sortOrderMap.get(key);
            if (columns == null) {
                columns = new LinkedHashSet<>();
            }
            columns.add(column);
            sortOrderMap.put(key, columns);
        }
    }
    
    public void add(String key, int columnNumber, SortOrder sortOrder) {
        Column column = new Column(columnNumber, sortOrder);
        add(key, column);
    }
    
    
    public void add(String key, int columnNumber) {
        add(key, columnNumber, SortOrder.ASC);
    }
    
    public void add(String key, int columnNumber, String sortOrderName) {
        SortOrder sortOrder = SortOrder.getValue(sortOrderName);
        add(key, columnNumber, sortOrder);
    }
    
    public String get(String key) {
        Set<Column> columns = sortOrderMap.get(key);
        if (columns == null) {
            return "";
        }
        return columns.stream().map(Column::toString)
                .reduce("", (previous, current)
                        -> (("".equals(previous)) ? previous : previous + ", ") + current);
    }
    
    public String getSql(String key) {
        String sql = get(key);
        if (!"".equals(sql)) {
            sql = " ORDER BY " + sql;
        }
        return sql;
    }

    public boolean containsKey(String key) {
        return sortOrderMap.containsKey(key);
    }
    
    public boolean isEmpty() {
        return sortOrderMap.isEmpty();
    }
    
    public String getDefaultKey() {
        if (isEmpty()) {
            return null;
        }
        return sortOrderMap.keySet().toArray(new String[sortOrderMap.size()])[0];
    }
}
