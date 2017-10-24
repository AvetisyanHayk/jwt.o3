package be.howest.jwt.o3.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayk
 */
public class Pagination {

    private Pagination() {
    }

    public static final List<Integer> PAGE_OFFSET_LIST = new ArrayList<>();

    static {
        PAGE_OFFSET_LIST.add(25);
        PAGE_OFFSET_LIST.add(50);
        PAGE_OFFSET_LIST.add(75);
        PAGE_OFFSET_LIST.add(100);
    }
    
    public static int getOffset(int key) {
        if (key >= 0 && key < PAGE_OFFSET_LIST.size()) {
            return PAGE_OFFSET_LIST.get(key);
        }
        return getOffset(0);
    }
}
