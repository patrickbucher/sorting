package sorting;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class ConcurrentMergeSortTask<T extends Comparable<T>> extends RecursiveAction {

    private final T items[];
    private final int min;
    private final int length;
    private final int threshold;

    public ConcurrentMergeSortTask(T items[], int threshold) {
        this.items = items;
        this.min = 0;
        this.length = items.length;
        this.threshold = threshold;
    }

    private ConcurrentMergeSortTask(T items[], int min, int length, int threshold) {
        this.items = items;
        this.min = min;
        this.length = length;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (length - min + 1 <= threshold) {
            Arrays.sort(items, min, length);
        } else {
            int mid = min + (length - min) / 2;
            invokeAll(new ConcurrentMergeSortTask<T>(items, min, mid, threshold),
                    new ConcurrentMergeSortTask<T>(items, mid, length, threshold));
            merge(min, mid, length);
        }
    }

    private void merge(int min, int mid, int max) {
        T buf[] = Arrays.copyOfRange(items, min, mid);
        int readBuf = 0;
        int readArray = mid;
        int write = min;
        while (readBuf < buf.length) {
            if (readArray == max || buf[readBuf].compareTo(items[readArray]) < 0) {
                items[write++] = buf[readBuf++];
            } else {
                items[write++] = items[readArray++];
            }
        }
    }

}
