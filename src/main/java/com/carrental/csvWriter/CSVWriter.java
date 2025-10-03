package com.carrental.csvWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.carrental.metrics.Metrics;

public class CSVWriter {
    private final String file;
    private boolean headerWritten = false;

    public CSVWriter(String file) {
        this.file = file;
    }

    public void write(String algorithm, int n, long timeMs, Metrics metrics) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            if (!headerWritten) {
                pw.println("ALGORITHM,N,TIME_MS,COMPARISONS,SWAPS,ARRAY_ACCESSES,MEMORY_ALLOCATIONS,RECURSION_DEPTH");
                headerWritten = true;
            }

            pw.printf("%s,%d,%d,%d,%d,%d,%d,%d%n",
                    algorithm,
                    n,
                    timeMs,
                    metrics.getComparisons(),
                    metrics.getSwaps(),
                    metrics.getArrayAccesses(),
                    metrics.getMemoryAllocations(),
                    metrics.getRecursionDepth()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
