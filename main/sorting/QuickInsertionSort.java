package sorting;

public class QuickInsertionSort<T extends Comparable<T>> implements Sort<T> {

    private int threshold;

    public QuickInsertionSort(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void sort(T[] items) {
        sort(items, 0, items.length - 1);
    }

    public void sort(T[] items, int left, int right) {
        int up = left;
        int down = right - 1;
        T t = items[right];
        do {
            while (items[up].compareTo(t) < 0) {
                up++;
            }
            while (items[down].compareTo(t) >= 0 && down > up) {
                down--;
            }
            if (up < down) {
                SortUtils.swap(items, up, down);
            }
        } while (up < down);
        SortUtils.swap(items, up, right);
        if (left < up - 1) {
            int size = up - left;
            if (size < threshold) {
                insertionSort(items, left, up - 1);
            } else {
                sort(items, left, up - 1);
            }
        }
        if (right > up + 1) {
            int size = right - up;
            if (size < threshold) {
                insertionSort(items, up + 1, right);
            } else {
                sort(items, up + 1, right);
            }
        }
    }

    private void insertionSort(T[] items, int from, int to) {
        for (int s = from + 1; s <= to; s++) {
            for (int i = s; i > 0 && items[i].compareTo(items[i - 1]) < 0; i--) {
                SortUtils.swap(items, i, i - 1);
            }
        }
    }

}
