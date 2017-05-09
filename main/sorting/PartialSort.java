package sorting;

public interface PartialSort<T extends Comparable<T>> {
    public void sortPartially(T items[], int from, int to);
}
