package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T>  madComparator;

    public MaxArrayDeque(Comparator<T> c) {
        madComparator = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1, len = size(); i < len; i++) {
            int res = madComparator.compare(get(maxIndex), get(i));
            if (res < 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }

    public T max(Comparator<T> c) {
        Comparator<T> tempComparator = madComparator;
        madComparator = c;
        T res = max();
        madComparator = tempComparator;
        return res;
    }

}
