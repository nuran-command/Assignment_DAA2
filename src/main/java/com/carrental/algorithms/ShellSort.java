package com.carrental.algorithms;

import com.carrental.metrics.Metrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShellSort {

    public enum GapSequence { SHELL, KNUTH, SEDGEWICK }

    // Public API called by BenchmarkRunner/tests
    public static void sort(int[] a, Metrics metrics, GapSequence seq) {
        if (a == null) throw new IllegalArgumentException("array is null");
        // caller may reset metrics; reset here for safety
        metrics.reset();

        int n = a.length;
        if (n < 2) return;

        int[] gaps = generateGaps(n, seq);
        for (int gap : gaps) {
            // gapped insertion sort
            for (int i = gap; i < n; i++) {
                int temp = read(a, i, metrics);
                int j = i;
                while (j >= gap && cmpVals(read(a, j - gap, metrics), temp, metrics) > 0) {
                    // shift element right
                    write(a, j, read(a, j - gap, metrics), metrics);
                    metrics.incrementSwaps(); // count shift as a move
                    j -= gap;
                }
                write(a, j, temp, metrics);
            }
        }
    }

    // --- helpers that account for metrics ---
    private static int read(int[] a, int i, Metrics m) {
        m.incrementArrayAccesses();
        return a[i];
    }

    private static void write(int[] a, int i, int val, Metrics m) {
        m.incrementArrayAccesses();
        a[i] = val;
    }

    private static int cmpVals(int x, int y, Metrics m) {
        m.incrementComparisons();
        return Integer.compare(x, y);
    }

    // --- gap generators ---
    private static int[] generateGaps(int n, GapSequence seq) {
        List<Integer> list = new ArrayList<>();
        switch (seq) {
            case SHELL:
                for (int gap = n / 2; gap > 0; gap /= 2) list.add(gap);
                break;

            case KNUTH:
                int h = 1;
                while (h < n) {
                    list.add(h);
                    h = 3 * h + 1;
                }
                Collections.reverse(list);
                break;

            case SEDGEWICK:
                // common Sedgewick increments (precomputed). Add those < n.
                int[] sedgewick = {1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289, 64769, 146305, 260609};
                for (int g : sedgewick) if (g < n) list.add(g);
                Collections.reverse(list);
                break;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
