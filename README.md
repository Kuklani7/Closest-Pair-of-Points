# Closest Pair of Points — High-Performance Geometric Solver

This project implements an efficient solution to the Closest Pair of Points problem using a divide-and-conquer approach optimized for large-scale geometric datasets. The system processes raw point data, parses it into structured representations, and computes the minimum Euclidean distance between any two points in (O(n \log n)) time.

A custom parsing pipeline was developed to ingest input data and convert it into an internal point-based model. The core algorithm recursively partitions the dataset, computes local minima, and efficiently evaluates cross-boundary candidates using a strip-based optimization. To support this, multiple variants of QuickSort were implemented, including lexicographic sorting by ((x, y)) and secondary sorting by ((y, x)) for localized comparisons.

The implementation emphasizes performance and low-level control, including manual memory handling, custom comparator logic, and optimized recursion patterns. Careful pivot selection strategies were integrated into QuickSort to avoid worst-case recursion depth and ensure stability on nearly sorted or duplicate-heavy datasets.

**Tech Stack:** Java, Custom QuickSort (multi-key sorting), Divide & Conquer Algorithms, Computational Geometry, Array-Based Data Structures, Recursive Optimization Techniques.
