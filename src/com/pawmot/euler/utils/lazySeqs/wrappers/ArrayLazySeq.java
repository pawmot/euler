package com.pawmot.euler.utils.lazySeqs.wrappers;

import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;

import java.math.BigInteger;

public class ArrayLazySeq<T> implements LazySeq<T> {
    private final T[] array;
    private final int currentIndex;

    public ArrayLazySeq(T[] array, int currentIndex) {
        assert array != null && array.length > 0 && currentIndex > -1 && currentIndex < array.length;

        this.array = array;
        this.currentIndex = currentIndex;
    }

    @Override
    public T head() {
        return array[currentIndex];
    }

    @Override
    public LazySeq<T> tail() {
        return currentIndex + 1 < array.length ? new ArrayLazySeq<>(array, currentIndex + 1) : new Empty<>();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T nth(int n) {
        return array[currentIndex + n];
    }

    @Override
    public int length32() {
        return array.length - currentIndex;
    }

    @Override
    public long length64() {
        return array.length - currentIndex;
    }

    @Override
    public BigInteger lengthBig() {
        return BigInteger.valueOf(array.length - currentIndex);
    }
}
