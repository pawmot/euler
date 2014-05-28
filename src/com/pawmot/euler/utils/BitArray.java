package com.pawmot.euler.utils;

import java.util.Arrays;

public final class BitArray {
    private int[] data;
    private int size;

    public BitArray(int size) {
        int n = size % 32 == 0 ? size/32 : size/32 + 1;
        this.data = new int[n];
        this.size = size;
    }

    public BitArray(int size, boolean initVal) {
        this(size);

        Arrays.fill(data, initVal ? ~0 : 0);
    }

    public boolean get(int index) {
        if(index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int n = index >> 5;
        int m = index - n;

        int c = 1 << m;
        return (data[n] & c) == c;
    }

    public void set(int index, boolean value) {
        if(index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int n = index >> 5;
        int m = index - n;

        if(value) {
            data[n] |= 1 << m;
        } else {
            data[n] &= ~(1 << m);
        }
    }
}
