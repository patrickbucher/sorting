package sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SortTest {

    private final static int QUICK_INSERTION_SORT_THRESHOLD = 25;
    
    private final static int TEST_SIZE = 10_000;

    private final static int SIMPLE_BENCHMARKS[] = { 1_000, 2_000, 5_000, 10_000, 20_000, 50_000 };
    private final static int HIGHER_BENCHMARKS[] = { 100_000, 200_000, 500_000, 1_000_000, 2_000_000, 5_000_000, 10_000_000, 20_000_000, 50_000_000 };

    private Integer[] numbers;

    private Sort<Integer> bubbleSort;
    private Sort<Integer> insertionSort;
    private Sort<Integer> selectionSort;
    private Sort<Integer> quickSort;
    private Sort<Integer> quickSortMo3;
    private Sort<Integer> quickInsertionSort;
    private Sort<Integer> heapSort;

    @Before
    public void init() {
        numbers = SortUtils.randomIntegerArray(TEST_SIZE, 0, TEST_SIZE);
        bubbleSort = new BubbleSort<>();
        insertionSort = new InsertionSort<>();
        selectionSort = new SelectionSort<>();
        quickSort = new QuickSort<>();
        quickSortMo3 = new QuickSortMedianOfThree<>();
        quickInsertionSort = new QuickInsertionSort<>(QUICK_INSERTION_SORT_THRESHOLD);
        heapSort = new HeapSort<>();
    }

    @Test
    public void testBubbleSort() {
        bubbleSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void testInsertionSort() {
        insertionSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void testSelectionSort() {
        selectionSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void testQuickSort() {
        quickSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }
    
    @Test
    public void testQuickSortMedianOfThree() {
        quickSortMo3.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void testQuickInsertionSort() {
        quickInsertionSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }
    
    @Test
    public void testHeapSort() {
        heapSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void benchmarkAlgorithms() {
        System.out.println(" Items    BS    IS    SS");
        System.out.println("------ ----- ----- -----");
        for (int size : SIMPLE_BENCHMARKS) {
            long bs = benchmark(bubbleSort, SortUtils.randomIntegerArray(size, 0, size));
            long is = benchmark(insertionSort, SortUtils.randomIntegerArray(size, 0, size));
            long ss = benchmark(selectionSort, SortUtils.randomIntegerArray(size, 0, size));
            System.out.printf("%6d %5d %5d %5d\n", size, bs, is, ss);
        }
        System.out.println("");
        System.out.println("   Items      HS      QS  QS Mo3     QIS");
        System.out.println("-------- ------- ------- ------- -------");
        for (int size : HIGHER_BENCHMARKS) {
            long hs = benchmark(heapSort, SortUtils.randomIntegerArray(size, 0, size));
            long qs = benchmark(quickSort, SortUtils.randomIntegerArray(size, 0, size));
            long qsM03 = benchmark(quickSortMo3, SortUtils.randomIntegerArray(size, 0, size));
            long qis = benchmark(quickInsertionSort, SortUtils.randomIntegerArray(size, 0, size));
            System.out.printf("%8d %7d %7d %7d %7d\n", size, hs, qs, qsM03, qis);
        }
    }

    private long benchmark(Sort<Integer> algorithm, Integer[] numbers) {
        long start = System.currentTimeMillis();
        algorithm.sort(numbers);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
