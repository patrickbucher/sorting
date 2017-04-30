package sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SortTest {

    private final static int TEST_SIZE = 10_000;

    private Integer[] numbers;

    private Sort<Integer> bubbleSort;
    private Sort<Integer> insertionSort;

    @Before
    public void init() {
        numbers = SortUtils.randomIntegerArray(TEST_SIZE, 0, TEST_SIZE);
        bubbleSort = new BubbleSort<>();
        insertionSort = new InsertionSort<>();
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
}
