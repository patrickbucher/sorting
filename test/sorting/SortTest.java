package sorting;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SortTest {

    private final static int SIMPLE_SORT_THRESHOLD = 25;

    private final static int TEST_SIZE = 10_000;

    private final static int SIMPLE_BENCHMARKS[] = { 1_000, 2_000, 5_000, 10_000, 20_000, 50_000 };
    private final static int HIGHER_BENCHMARKS[] = { 100_000, 200_000, 500_000, 1_000_000, 2_000_000, 5_000_000 };
    private final static int CONCURRENT_BENCHMARKS[] = { 1_000_000, 2_000_000, 5_000_000, 10_000_000, 20_000_000,
            50_000_000 };

    private Integer[] numbers;

    private Sort<Integer> bubbleSort;
    private Sort<Integer> insertionSort;
    private Sort<Integer> selectionSort;
    private Sort<Integer> quickSort;
    private Sort<Integer> quickSortMo3;
    private Sort<Integer> quickInsertionSort;
    private Sort<Integer> heapSort;
    private Sort<Integer> mergeSort;
    private Sort<Integer> concurrentMergeSort;
    private Sort<Integer> concurrentQuickSort;

    @Before
    public void init() {
        numbers = SortUtils.randomIntegerArray(TEST_SIZE, 0, TEST_SIZE);
        bubbleSort = new BubbleSort<>();
        insertionSort = new InsertionSort<>();
        selectionSort = new SelectionSort<>();
        quickSort = new QuickSort<>();
        quickSortMo3 = new QuickSortMedianOfThree<>();
        quickInsertionSort = new QuickInsertionSort<>(new InsertionSort<Integer>(), SIMPLE_SORT_THRESHOLD);
        heapSort = new HeapSort<>();
        mergeSort = new MergeSort<>(new InsertionSort<Integer>(), SIMPLE_SORT_THRESHOLD);
        concurrentMergeSort = new ConcurrentMergeSort<>(SIMPLE_SORT_THRESHOLD);
        concurrentQuickSort = new ConcurrentQuickSort<>(SIMPLE_SORT_THRESHOLD);
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
    public void testMergeSort() {
        mergeSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void testConcurrentMergeSort() {
        concurrentMergeSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void testConcurrentQuickSort() {
        concurrentQuickSort.sort(numbers);
        Assert.assertTrue(SortUtils.sorted(numbers));
    }

    @Test
    public void benchmarkSimpleAlgorithms() {
        System.out.println("");
        System.out.println(" Items    BS    IS    SS");
        System.out.println("------ ----- ----- -----");
        for (int size : SIMPLE_BENCHMARKS) {
            Integer bsItems[] = SortUtils.randomIntegerArray(size, 0, size);
            Integer isItems[] = Arrays.copyOf(bsItems, bsItems.length - 1);
            Integer ssItems[] = Arrays.copyOf(bsItems, bsItems.length - 1);
            long bs = benchmark(bubbleSort, bsItems);
            long is = benchmark(insertionSort, isItems);
            long ss = benchmark(selectionSort, ssItems);
            System.out.printf("%6d %5d %5d %5d\n", size, bs, is, ss);
        }
    }

    @Test
    public void benchmarkHigherAlgorithms() {
        System.out.println("");
        System.out.println("   Items      HS      QS  QS Mo3     QIS      MS");
        System.out.println("-------- ------- ------- ------- ------- -------");
        for (int size : HIGHER_BENCHMARKS) {
            Integer hItems[] = SortUtils.randomIntegerArray(size, 0, size);
            Integer qItems[] = Arrays.copyOf(hItems, hItems.length - 1);
            Integer qMo3Items[] = Arrays.copyOf(hItems, hItems.length - 1);
            Integer qisItems[] = Arrays.copyOf(hItems, hItems.length - 1);
            Integer msItems[] = Arrays.copyOf(hItems, hItems.length - 1);
            long hs = benchmark(heapSort, hItems);
            long qs = benchmark(quickSort, qItems);
            long qsMo3 = benchmark(quickSortMo3, qMo3Items);
            long qis = benchmark(quickInsertionSort, qisItems);
            long ms = benchmark(mergeSort, msItems);
            System.out.printf("%8d %7d %7d %7d %7d %7d\n", size, hs, qs, qsMo3, qis, ms);
        }
    }

    @Test
    public void benchmarkConcurrentAlgorithms() {
        System.out.println("");
        System.out.println("   Items     CMS     CQS");
        System.out.println("-------- ------- -------");
        for (int size : CONCURRENT_BENCHMARKS) {
            Integer cmsItems[] = SortUtils.randomIntegerArray(size, 0, size);
            Integer cqsItems[] = Arrays.copyOf(cmsItems, cmsItems.length - 1);
            long cms = benchmark(concurrentMergeSort, cmsItems);
            long cqs = benchmark(concurrentQuickSort, cqsItems);
            System.out.printf("%8d %7d %7d\n", size, cms, cqs);
        }
    }

    @Test
    public void testPartialSort() {
        Integer numbers[] = new Integer[] { 100, 5, 4, 3, 2, 1, -100 };
        PartialSort<Integer> partialSort = new InsertionSort<>();
        partialSort.sortPartially(numbers, 1, numbers.length - 1);
        System.out.println(Arrays.toString(numbers));
        Assert.assertArrayEquals(new Integer[] { 100, 1, 2, 3, 4, 5, -100 }, numbers);
    }

    private long benchmark(Sort<Integer> algorithm, Integer[] numbers) {
        long start = System.currentTimeMillis();
        algorithm.sort(numbers);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
