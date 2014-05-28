package com.pawmot.euler.utils.streams;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface LazySeq<T> {
    public T head();
    public LazySeq<T> tail();
    public boolean isEmpty();

    default public LazySeq<T> filter(Predicate<T> f) {
        if(this.isEmpty()) return this;

        return FilteredLazySeq.create(this, f);
    }

    default public <U> U foldLeft(BiFunction<U, T, U> op, U accumulator) {
        if(this.isEmpty()) return accumulator;

        LazySeq<T> c = this;

        while(!c.isEmpty()) {
            accumulator = op.apply(accumulator, c.head());
            c = c.tail();
        }

        return accumulator;
    }

    default public T reduceLeft(BiFunction<T, T, T> op) {
        if(this.isEmpty()) throw new UnsupportedOperationException("Can't reduce an empty sequence");
        else return this.tail().foldLeft(op, this.head());
    }

    default public <U> LazySeq<U> map(Function<T, U> f) {
        if(this.isEmpty()) return new Empty<>();
        else return new MappedLazySeq<>(this, f);
    }

    default public LazySeq<T> filterWhile(Predicate<T> f) {
        if(this.isEmpty()) return this;
        if(!f.test(this.head())) return new Empty<>();
        else return new FilterWhileLazySeq<>(this, f);
    }

    default public LazySeq<T> take(int n) {
        if(n < 0) throw new IllegalArgumentException();
        if(n == 0) return new Empty<>();
        else return new BoundedLazySeq<>(this, n);
    }
}
