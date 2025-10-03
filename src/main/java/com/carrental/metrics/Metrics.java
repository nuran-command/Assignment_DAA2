package com.carrental.metrics;

public class Metrics {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long memoryAllocations = 0;

    private long recursionDepth = 0;
    private long currDepth = 0;

    // comparisons
    public void incrementComparisons() {
        comparisons++;
    }

    public long getComparisons() {
        return comparisons;
    }

    // swaps
    public void incrementSwaps() {
        swaps++;
    }

    public long getSwaps() {
        return swaps;
    }

    // array
    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    // memory
    public void incrementMemoryAllocations() {
        memoryAllocations++;
    }

    public long getMemoryAllocations() {
        return memoryAllocations;
    }

    // recursion
    public void enterRecursion() {
        currDepth++;
        recursionDepth = Math.max(recursionDepth, currDepth);
    }

    public void exitRecursion() {
        currDepth--;
    }

    public long getRecursionDepth() {
        return recursionDepth;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
        recursionDepth = 0;
        currDepth = 0;
    }
}

