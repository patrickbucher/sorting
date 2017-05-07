package sorting;

import java.util.concurrent.ForkJoinPool;

public class ConcurrentMergeSort<T extends Comparable<T>> implements Sort<T> {

    private final int threshold;

    public ConcurrentMergeSort(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void sort(T[] items) {
        ForkJoinPool pool = new ForkJoinPool();
        ConcurrentMergeSortTask<T> task = new ConcurrentMergeSortTask<>(items, threshold);
        pool.invoke(task);
    }

}
