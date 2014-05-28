package com.pawmot.euler.utils.lazySeqs;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
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

        for(LazySeq<T> c = this; !c.isEmpty(); c = c.tail()) {
            accumulator = op.apply(accumulator, c.head());
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

    default public T firstOrNull(Predicate<T> f) {
        for(LazySeq<T> c = this; !c.isEmpty(); c = c.tail()) {
            if(f.test(c.head())) return c.head();
        }

        return null;
    }

    default public LazySeq<T> distinct() {
        Set<T> set = new HashSet<>();

        return DistinctLazySeq.create(this, set);
    }

    default public boolean all(Predicate<T> f) {
        for(LazySeq<T> c = this; !c.isEmpty(); c = c.tail()) {
            if(!f.test(c.head())) return false;
        }

        return true;
    }

    default public boolean any(Predicate<T> f) {
        for(LazySeq<T> c = this; !c.isEmpty(); c = c.tail()) {
            if(f.test(c.head())) return true;
        }

        return false;
    }

    default public void forEach(Consumer<T> f) {
        for(LazySeq<T> c = this; !c.isEmpty(); c = c.tail()) {
            f.accept(c.head());
        }
    }

    default public T nth(int n) {
        if(n < 0) throw new IndexOutOfBoundsException();

        LazySeq<T> c = this;
        for(; n > 0 && !c.isEmpty(); c = c.tail(), n--);

        if(n == 0) return c.head();
        else throw new IndexOutOfBoundsException();
    }
}
