package be.howest.jwt.o3.data;

/**
 *
 * @author Hayk
 */
public enum SingleFilterOperator {
    EQUALS, LIKE;
    
    @Override
    public String toString() {
        if (EQUALS.equals(this)) {
            return " = ";
        }
        return super.toString();
    }
}
