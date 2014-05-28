package com.pawmot.euler.utils.memo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Memoizer1<T, R> implements Function<T, R> {
    Map<T, R> cache = new HashMap<>();

    @Override
    public R apply(T arg) {
        if(cache.containsKey(arg)) {
            return cache.get(arg);
        } else {
            R result = this.compute(arg);
            cache.put(arg, result);
            return result;
        }
    }

    protected abstract R compute(T arg);
}
