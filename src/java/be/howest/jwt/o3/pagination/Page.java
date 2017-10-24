package be.howest.jwt.o3.pagination;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Hayk
 * @param <E>
 */
public class Page<E> {

    private int pageNumber;
    private int offset;
    private int totalEntities;
    private Set<E> entities = new LinkedHashSet<>();
    private PageSort pageSort;
    private String sortOrderKey;
    private static final int DEFAULT_OFFSET = 25;

    public Page() {
        this(1, DEFAULT_OFFSET);
    }
    
    public Page(Page page) {
        this(page.pageNumber, page.offset);
        setSortOrderKey(page.sortOrderKey);
    }
    
    public Page(int offset) {
        this(1, offset);
    }

    public Page(int pageNumber, int offset) {
        setPageNumber(pageNumber);
        setOffset(offset);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public final void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public int getOffset() {
        return offset;
    }

    public final void setOffset(int offset) {
        if (offset <= 1) {
            offset = DEFAULT_OFFSET;
        }
        if (this.offset != 0 && offset != this.offset) {
            setPageNumber(1);
        }
        this.offset = offset;
    }

    public int getTotalEntities() {
        return totalEntities;
    }

    public void setTotalEntities(int totalEntities) {
        if (totalEntities >= 0) {
            this.totalEntities = totalEntities;
        }
    }

    public int getTotalPages() {
        return totalEntities / offset;
    }

    public Set<E> getEntities() {
        return Collections.unmodifiableSet(entities);
    }
    
    public void add(E entity) {
        entities.add(entity);
    }

    public void setEntities(Set<E> entities) {
        this.entities = entities;
    }

    public PageSort getPageSort() {
        return pageSort;
    }

    public void setPageSort(PageSort pageSort) {
        this.pageSort = pageSort;
        setSortOrderKey(pageSort.getDefaultKey());
    }

    public String getSortOrderKey() {
        return sortOrderKey;
    }

    public final void setSortOrderKey(String sortOrderKey) {
        if (pageSort.containsKey(sortOrderKey)) {
            this.sortOrderKey = sortOrderKey;
        } else if (this.sortOrderKey == null) {
            this.sortOrderKey = pageSort.getDefaultKey();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (pageSort != null) {
            sb.append(pageSort.getSql(sortOrderKey));
        }
        return sb.append(" LIMIT ")
                .append(pageNumber - 1)
                .append(", ").append(offset)
                .toString();
    }

}
