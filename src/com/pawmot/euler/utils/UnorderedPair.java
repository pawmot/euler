package com.pawmot.euler.utils;

// TODO: think over the UnorderedPair API - how does having a "_1" and "_2" element make sense if it's *unordered*?
public final class UnorderedPair<T> implements Pair<T, T> {
    private T _1;
    private T _2;

    private UnorderedPair(T _1, T _2) {
        assert _1 != null && _2 != null;

        this._1 = _1;
        this._2 = _2;
    }

    public T get_1() {
        return _1;
    }

    public T get_2() {
        return _2;
    }

    public static <T> UnorderedPair<T> from(T _1, T _2) {
        return new UnorderedPair<>(_1, _2);
    }

    @Override
    public String toString() {
        return "(" + _1 + ", " + _2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnorderedPair unorderedPair = (UnorderedPair) o;

        return (_1.equals(unorderedPair._1) && _2.equals(unorderedPair._2)) || (_1.equals(unorderedPair._2) && _2.equals(unorderedPair._1));

    }

    @Override
    public int hashCode() {
        int h1 = _1.hashCode();
        int h2 = _2.hashCode();

        if(h1 < h2) {
            // swap
            h1 = h1 ^ h2;
            h2 = h1 ^ h2;
            h1 = h1 ^ h2;
        }

        return 31 * h1 + h2;
    }
}
