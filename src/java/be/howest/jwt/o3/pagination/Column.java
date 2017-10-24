package be.howest.jwt.o3.pagination;

/**
 *
 * @author Hayk
 */
public class Column {

    private final int colNumber;
    private final SortOrder sortOrder;

    public Column(int colNumber) {
        this(colNumber, SortOrder.ASC);
    }

    public Column(int colNumber, SortOrder sortOrder) {
        this.colNumber = colNumber;
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(colNumber));
        if (sortOrder == SortOrder.DESC) {
            sb.append(" ").append(sortOrder);
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.colNumber;
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
        final Column other = (Column) obj;
        return colNumber == other.colNumber;
    }

}
