package be.howest.jwt.o3.data;

/**
 *
 * @author Hayk
 * @param <T>
 */
public class SingleFilter<T> extends DataFilter<T> {

    private final SingleFilterOperator operator;
    private final T value;
    
    public SingleFilter(String key, T value) {
        this(key, value, SingleFilterOperator.EQUALS);
    }
     
    public SingleFilter(String key, T value, SingleFilterOperator operator) {
        this.key = key;
        if (operator == null) {
            operator = SingleFilterOperator.EQUALS;
        }
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }
    
    @Override
    public String toString() {
        if (key == null ) {
            return "";
        }
        return new StringBuilder()
                .append(key).append(" ")
                .append(operator).append(" '")
                .append(value).append("'")
                .toString();
    }

    @Override
    public boolean isValid() {
        return key != null && value != null && !"".equals(value) && operator != null;
    }

}
