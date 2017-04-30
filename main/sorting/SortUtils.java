package sorting;

import java.util.Random;

public class SortUtils {

    public static <T> void swap(T items[], int a, int b) {
        T tmp = items[a];
        items[a] = items[b];
        items[b] = tmp;
    }

    public static Integer[] randomIntegerArray(int size, int min, int max) {
        Integer integers[] = new Integer[size];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt((max - min) + 1) + min;
        }
        return integers;
    }

    public static <T extends Comparable<T>> boolean sorted(T items[]) {
        T current, last = items[0];
        for (int i = 1; i < items.length; i++) {
            current = items[i];
            if (current.compareTo(last) < 0) {
                return false;
            }
            last = current;
        }
        return true;
    }

    public static <T extends Comparable<T>> boolean equals(T a[], T b[]) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }
}
