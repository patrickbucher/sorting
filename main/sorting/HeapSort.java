package sorting;

public class HeapSort<T extends Comparable<T>> implements Sort<T> {

    public void sort(T[] items) {
        Heap<T> heap = new Heap<>();
        for (int i = 0; i < items.length; i++) {
            heap.insert(items[i]);
        }
        for (int i = items.length - 1; i >= 0; i--) {
            items[i] = heap.getMax();
        }
    }
}
