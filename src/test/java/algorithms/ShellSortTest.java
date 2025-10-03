package algorithms;
import com.carrental.algorithms.ShellSort;

import com.carrental.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {

    @Test
    public void emptyArray() {
        int[] a = new int[0];
        Metrics m = new Metrics();
        ShellSort.sort(a, m, ShellSort.GapSequence.SHELL);
        assertArrayEquals(new int[0], a);
    }

    @Test
    public void singleElement() {
        int[] a = {42};
        Metrics m = new Metrics();
        ShellSort.sort(a, m, ShellSort.GapSequence.KNUTH);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    public void sortedAndReverse() {
        int[] s = {1,2,3,4,5};
        int[] r = {5,4,3,2,1};
        Metrics m = new Metrics();
        ShellSort.sort(s, m, ShellSort.GapSequence.SEDGEWICK);
        ShellSort.sort(r, m, ShellSort.GapSequence.SEDGEWICK);
        assertArrayEquals(new int[]{1,2,3,4,5}, s);
        assertArrayEquals(new int[]{1,2,3,4,5}, r);
    }

    @Test
    public void randomizedSmall() {
        for (int n : new int[]{0,1,5,50}) {
            for (int seed = 0; seed < 5; seed++) {
                int[] a = randomArray(n, seed);
                int[] expected = Arrays.copyOf(a, a.length);
                Arrays.sort(expected);
                Metrics m = new Metrics();
                ShellSort.sort(a, m, ShellSort.GapSequence.KNUTH);
                assertArrayEquals(expected, a);
            }
        }
    }

    private int[] randomArray(int n, int seed) {
        java.util.Random r = new java.util.Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(1000);
        return a;
    }
}