package sorting;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> {

    private final List<T> heap = new ArrayList<>();

    public T getMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }
        T max = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        sink();
        return max;
    }

    public void insert(T element) {
        heap.add(element);
        int newElementIndex = heap.size() - 1;
        raise(newElementIndex);
    }

    public int getSize() {
        return heap.size();
    }

    private void sink() {
        final int size = heap.size();
        boolean sunk = false;
        int l = 1, f = 0, r = 2;
        while (!sunk && (l < size || r < size)) {
            T father = heap.get(f);
            T left = l < size ? heap.get(l) : father;
            T right = r < size ? heap.get(r) : father;
            if (father.compareTo(left) < 0 || father.compareTo(right) < 0) {
                int biggerChildIndex = left.compareTo(right) > 0 ? l : r;
                swap(f, biggerChildIndex);
                f = biggerChildIndex;
                l = (2 * f) + 1;
                r = 2 * (f + 1);
            } else {
                sunk = true;
            }
        }
    }

    private void raise(int i) {
        boolean risen = false;
        while (!risen) {
            int father = (i - 1) / 2;
            if (heap.get(i).compareTo(heap.get(father)) > 0) {
                swap(i, father);
                i = father;
                if (i == 0) {
                    risen = true;
                }
            } else {
                risen = true;
            }
        }
    }

    private void swap(int a, int b) {
        T tmp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, tmp);
    }
}
