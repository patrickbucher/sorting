package sorting;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    private final PartialSort<T> algorithmSmallN;
    private final int thresholdSmallN;
    
    public MergeSort(PartialSort<T> algorithmSmallN, int thresholdSmallN) {
        this.algorithmSmallN = algorithmSmallN;
        this.thresholdSmallN = thresholdSmallN;
    }
    
    @Override
    public void sort(T[] items) {
        sort(items, 0, items.length - 1);
    }

    public void sort(T[] items, int min, int max) {
        if (max - min + 1 < thresholdSmallN) {
            algorithmSmallN.sortPartially(items, min, max + 1);
        } else {
            int mid = min + (max - min + 1) / 2;
            sort(items, min, mid);
            sort(items, mid, max);
            merge(items, min, mid, max);
        }

    }

    private void merge(T items[], int min, int mid, int max) {
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
