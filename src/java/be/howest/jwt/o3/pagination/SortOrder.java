package be.howest.jwt.o3.pagination;

/**
 *
 * @author Hayk
 */
public enum SortOrder {
    ASC, DESC;

    public static SortOrder getValue(String name) {
        SortOrder value = SortOrder.ASC;
        try {
            value = SortOrder.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return value;
    }
}
