package sorting;

public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] items) {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items.length; j++) {
                if (items[i].compareTo(items[j]) < 0) {
                    SortUtils.swap(items, i, j);
                }
            }
        }
    }

}
