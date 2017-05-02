package sorting;

public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] items) {
        boolean sorted = false;
        for (int i = 0; !sorted && i < items.length; i++) {
            sorted = true;
            for (int j = 0; j < items.length; j++) {
                if (items[i].compareTo(items[j]) < 0) {
                    SortUtils.swap(items, i, j);
                    sorted = false;
                }
            }
        }
    }

}
