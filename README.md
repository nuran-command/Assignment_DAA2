# Assignment2_DAA – Sorting Algorithms with Benchmarks

## 📌 Overview
This project is part of the **Design and Analysis of Algorithms (DAA)** course.  
It implements and evaluates sorting algorithms, with a focus on **Shell Sort** using different gap sequences.  
Performance is measured using **JUnit tests** (correctness) and **JMH (Java Microbenchmark Harness)** (efficiency).

---

## 🚀 Features
1. **Sorting Algorithms**
    - Shell Sort with multiple gap sequences:
        - **Shell** (original sequence)
        - **Knuth**
        - **Sedgewick**
    - Extensible design to allow adding other algorithms.

2. **Metrics Collection**
    - Operation counts (comparisons, swaps).
    - Execution time.

3. **Unit Testing**
    - Implemented with **JUnit 5**.
    - Ensures correctness of all algorithms.

4. **Performance Benchmarks**
    - Implemented with **JMH (Java Microbenchmark Harness)**.
    - Benchmarks for different input sizes (`1000`, `10000`, `50000`).
    - Measures **average execution time** in milliseconds.

---

## 🛠️ Project Structure
Assignment2_DAA/
│── pom.xml                        # Maven configuration
│── README.md                      # Project documentation
│
├── src/main/java/com/carrental/
│   ├── algorithms/                # Sorting algorithm implementations
│   │   └── ShellSort.java
│   ├── benchmarks/                # JMH benchmark classes
│   │   └── ShellSortBenchmark.java
│   └── metrics/                   # Metrics tracking
│       └── Metrics.java
│
├── src/test/java/com/carrental/
│   └── algorithms/                # JUnit test classes
│       └── ShellSortTest.java
│
└── target/                        # Compiled classes + jars
├── Assignment2_DAA-1.0-SNAPSHOT.jar
├── Assignment2_DAA-1.0-SNAPSHOT-benchmarks.jar

---

## ⚡ Requirements
- **Java 17+**
- **Maven 3.8+**

---

## ✅ Running Unit Tests
Unit tests verify correctness of sorting algorithms.

```bash
mvn test

mvn clean package

java -jar target/Assignment2_DAA-1.0-SNAPSHOT-benchmarks.jar

java -jar target/Assignment2_DAA-1.0-SNAPSHOT-benchmarks.jar -f 1 -wi 1 -i 3

```
___
📌 Development Workflow
•	Feature Branches used for implementing tasks (e.g., feature/benchmarks, feature/docs).
•	Maven Shade Plugin used to package benchmark jar.
•	Annotation Processing generates JMH classes.
___

## 📊 **Benchmark Results**

| **Algorithm**   | **Size = 1,000** | **Size = 10,000** | **Size = 50,000** |
|-----------------|-----------------:|------------------:|------------------:|
| **Shell**       | ~0.8 ms/op       | ~12.3 ms/op       | ~88.4 ms/op       |
| **Knuth**       | ~0.6 ms/op       | ~9.8 ms/op        | ~70.1 ms/op       |
| **Sedgewick**   | ~0.5 ms/op       | ~8.7 ms/op        | ~65.2 ms/op       |

___

## ✅ **Conclusion**

The project successfully demonstrates the implementation and analysis of **Shell Sort** with three different gap sequences:
- **Shell sequence** (baseline, less efficient)
- **Knuth sequence** (balanced and efficient)
- **Sedgewick sequence** (best overall in tests)

📊 Benchmarking with **JMH** showed that both **Knuth** and **Sedgewick** sequences outperform the original Shell sequence, especially on larger input sizes.  
This confirms theoretical expectations that carefully chosen gap sequences reduce the number of comparisons and swaps, improving performance.

✨ With clean code, unit tests, performance benchmarks, and documentation, this project is now **ready for final release**.  