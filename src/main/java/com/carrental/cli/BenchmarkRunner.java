package com.carrental.cli;

import com.carrental.algorithms.ShellSort;
import com.carrental.metrics.Metrics;
import com.carrental.csvWriter.CSVWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        Map<String, String> opts = parseArgs(args);

        String algorithm = opts.getOrDefault("algorithm", "shell");
        String gap = opts.getOrDefault("gap", "shell");
        int size = Integer.parseInt(opts.getOrDefault("size", "10000"));
        String dist = opts.getOrDefault("dist", "random");
        int trials = Integer.parseInt(opts.getOrDefault("trials", "5"));
        String out = opts.getOrDefault("out", "results.csv");

        CSVWriter csv = new CSVWriter(out);
        Metrics metrics = new Metrics();

        for (int t = 0; t < trials; t++) {
            int[] arr = generate(size, dist, t);
            int[] copy = Arrays.copyOf(arr, arr.length);

            metrics.reset();
            long start = System.nanoTime();

            if ("shell".equalsIgnoreCase(algorithm)) {
                ShellSort.GapSequence seq;
                try {
                    seq = ShellSort.GapSequence.valueOf(gap.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("unknown gap sequence: " + gap + ". using SHELL");
                    seq = ShellSort.GapSequence.SHELL;
                }
                ShellSort.sort(copy, metrics, seq);
            } else {
                System.err.println("Unsupported algorithm: " + algorithm);
                return;
            }

            long timeMs = (System.nanoTime() - start) / 1_000_000;
            csv.write(algorithm + "-" + gap, size, timeMs, metrics);
            System.out.printf("trial=%d n=%d time=%dms comps=%d swaps=%d accesses=%d%n",
                    t, size, timeMs, metrics.getComparisons(), metrics.getSwaps(), metrics.getArrayAccesses());
        }
    }

    private static int[] generate(int n, String dist, int seed) {
        Random r = new Random(12345 + seed);
        int[] a = new int[n];

        switch (dist.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "nearly":
                for (int i = 0; i < n; i++) a[i] = i;
                // do O(n/100) random swaps
                for (int k = 0; k < Math.max(1, n / 100); k++) {
                    int i = r.nextInt(n);
                    int j = r.nextInt(n);
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
                break;
            case "duplicates":
                for (int i = 0; i < n; i++) a[i] = r.nextInt(Math.max(2, n / 100));
                break;
            default:
                for (int i = 0; i < n; i++) a[i] = r.nextInt();
        }
        return a;
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> out = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            if (a.startsWith("--") && i + 1 < args.length) {
                out.put(a.substring(2), args[++i]);
            }
        }
        return out;
    }
}

