package com.pawmot.euler.utils.lazySeqs.factories;

import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;
import com.pawmot.euler.utils.lazySeqs.wrappers.ArrayLazySeq;

import java.util.ArrayList;

public final class CollectionsSeqs {
    private CollectionsSeqs() {}

    public static <T> LazySeq<T> from(T[] array) {
        if(array == null || array.length == 0) return new Empty<>();

        return new ArrayLazySeq<>(array, 0);
    }

    @SuppressWarnings("unchecked")
    public static <T> LazySeq<T> from(ArrayList<T> list) {
        if(list == null) return new Empty<>();

        T[] arr = (T[]) new Object[list.size()];
        arr = list.toArray(arr);

        return from(arr);
    }
}
