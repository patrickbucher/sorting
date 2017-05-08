package sorting;

import java.util.concurrent.ForkJoinPool;

public class ConcurrentQuickSort<T extends Comparable<T>> implements Sort<T> {

    private final int threshold;

    public ConcurrentQuickSort(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void sort(T[] items) {
        ForkJoinPool pool = new ForkJoinPool();
        ConcurrentQuickSortTask<T> task = new ConcurrentQuickSortTask<>(items, threshold);
        pool.invoke(task);
    }

}
