package sorting;

public class SelectionSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] items) {
        for (int s = 0; s < items.length; s++) {
            int i = smallest(items, s, items.length - 1);
            SortUtils.swap(items, s, i);
        }
    }

    private int smallest(T[] items, int from, int to) {
        T smallest = items[from];
        int smallestIndex = from;
        for (int i = from; i <= to; i++) {
            if (items[i].compareTo(smallest) < 0) {
                smallest = items[i];
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

}
