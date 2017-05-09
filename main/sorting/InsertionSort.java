package sorting;

public class InsertionSort<T extends Comparable<T>> implements Sort<T>, PartialSort<T> {

    @Override
    public void sort(T[] items) {
        for (int s = 1; s < items.length; s++) {
            for (int i = s; i > 0 && items[i].compareTo(items[i - 1]) < 0; i--) {
                SortUtils.swap(items, i, i - 1);
            }
        }
    }

    @Override
    public void sortPartially(T[] items, int fromIn, int toEx) {
        for (int s = fromIn + 1; s < toEx; s++) {
            for (int i = s; i > fromIn && items[i].compareTo(items[i - 1]) < 0; i--) {
                SortUtils.swap(items, i, i - 1);
            }
        }
    }

}
