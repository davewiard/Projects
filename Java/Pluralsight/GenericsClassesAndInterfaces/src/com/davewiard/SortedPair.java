package com.davewiard;

public class SortedPair<T extends Comparable<T>> {
    private T first;
    private T second;

    public SortedPair(T left, T right) {
        if (left.compareTo(right) < 0) {
            this.first = left;
            this.second = right;
        } else {
            this.first = right;
            this.second = left;
        }
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }
}
