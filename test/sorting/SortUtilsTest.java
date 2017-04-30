package sorting;

import org.junit.Assert;
import org.junit.Test;

public class SortUtilsTest {

    @Test
    public void testSwap() {
        Integer numbers[] = new Integer[] { 1, 2, 3, 4, 5 };
        SortUtils.swap(numbers, 0, 4);
        boolean equals = SortUtils.equals(new Integer[] { 5, 2, 3, 4, 1 }, numbers);
        Assert.assertTrue(equals);
    }

    @Test
    public void testRandomIntegerArray() {
        final int n = 1_000_000, min = 1, max = 100;
        Integer numbers[] = SortUtils.randomIntegerArray(n, min, max);
        Assert.assertTrue(numbers.length == n);
        for (int i = 0; i < n; i++) {
            Assert.assertTrue(numbers[i] >= min && numbers[i] <= max);
        }
    }

    @Test
    public void testSorted() {
        Integer sorted[] = new Integer[] { 1, 1, 2, 3, 3 };
        Assert.assertTrue(SortUtils.sorted(sorted));
    }

    @Test
    public void testUnorted() {
        Integer unsorted[] = new Integer[] { 1, 2, 1, 3, 3 };
        Assert.assertFalse(SortUtils.sorted(unsorted));
    }

    @Test
    public void testEquals() {
        Integer a[] = { 1, 2, 3, 4, 5 };
        Integer b[] = { 1, 2, 3, 4, 5 };
        Assert.assertTrue(SortUtils.equals(a, b));
    }

    @Test
    public void testNotEquals() {
        Integer a[] = { 1, 2, 3, 4, 5 };
        Integer b[] = { 1, 2, 3, 5, 4 };
        Assert.assertFalse(SortUtils.equals(a, b));
    }
}
