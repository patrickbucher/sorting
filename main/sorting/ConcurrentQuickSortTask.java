package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class ConcurrentQuickSortTask<T extends Comparable<T>> extends RecursiveAction {

    private final T items[];
    private final int left;
    private final int right;
    private final int threshold;

    public ConcurrentQuickSortTask(T items[], int threshold) {
        this.items = items;
        this.left = 0;
        this.right = items.length - 1;
        this.threshold = threshold;
    }

    private ConcurrentQuickSortTask(T items[], int left, int right, int threshold) {
        this.items = items;
        this.left = left;
        this.right = right;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
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
        List<ConcurrentQuickSortTask<T>> tasks = new ArrayList<>();
        if (left < up - 1) {
            int size = (up - 1) - left + 1;
            if (size < threshold) {
                Arrays.sort(items, left, up);
            } else {
                tasks.add(new ConcurrentQuickSortTask<>(items, left, up - 1, threshold));
            }
        }
        if (right > up + 1) {
            int size = right - (up + 1) + 1;
            if (size < threshold) {
                Arrays.sort(items, up + 1, right + 1);
            } else {
                tasks.add(new ConcurrentQuickSortTask<>(items, up + 1, right, threshold));
            }
        }
        if (!tasks.isEmpty()) {
            invokeAll(tasks);
        }
    }

}
