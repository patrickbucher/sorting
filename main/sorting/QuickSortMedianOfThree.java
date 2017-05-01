package sorting;

public class QuickSortMedianOfThree<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] items) {
        sort(items, 0, items.length - 1);
    }

    public void sort(T[] items, int left, int right) {
        int up = left;
        int down = right - 1;
        int tIndex = medianOfThree(items, left, right);
        T t = items[tIndex];
        SortUtils.swap(items, tIndex, right);
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

    private <E extends Comparable<E>> int medianOfThree(E items[], int left, int right) {
        int middle = left + ((right - left) / 2);
        E l = items[left];
        E m = items[middle];
        E r = items[right];
        if (l.compareTo(r) > 0 && l.compareTo(m) < 0 || l.compareTo(r) < 0 && l.compareTo(m) > 0) {
            return left;
        } else if (m.compareTo(r) > 0 && m.compareTo(l) < 0 || m.compareTo(r) < 0 && m.compareTo(l) > 0) {
            return middle;
        } else {
            return right;
        }
    }

}
