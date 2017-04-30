package sorting;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

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
            sort(items, left, up - 1);
        }
        if (right > up + 1) {
            sort(items, up + 1, right);
        }
    }

}
