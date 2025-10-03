package com.carrental.benchmarks;

import com.carrental.algorithms.ShellSort;
import com.carrental.metrics.Metrics;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)          // measure average execution time
@OutputTimeUnit(TimeUnit.MILLISECONDS)    // report in ms
public class ShellSortBenchmark {

    private int[] data;
    private Metrics metrics;

    @Param({"1000", "10000", "50000"})   // benchmark different input sizes
    private int size;

    @Setup(Level.Iteration)
    public void setup() {
        Random r = new Random(12345);
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = r.nextInt();
        }
        metrics = new Metrics();
    }

    @Benchmark
    public void shellSortShell() {
        int[] copy = data.clone();
        ShellSort.sort(copy, metrics, ShellSort.GapSequence.SHELL);
    }

    @Benchmark
    public void shellSortKnuth() {
        int[] copy = data.clone();
        ShellSort.sort(copy, metrics, ShellSort.GapSequence.KNUTH);
    }

    @Benchmark
    public void shellSortSedgewick() {
        int[] copy = data.clone();
        ShellSort.sort(copy, metrics, ShellSort.GapSequence.SEDGEWICK);
    }
}