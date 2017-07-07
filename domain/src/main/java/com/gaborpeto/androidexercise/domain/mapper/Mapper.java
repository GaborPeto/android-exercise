package com.gaborpeto.androidexercise.domain.mapper;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleFlatMapIterableObservable;

public abstract class Mapper<T, U> {

    public Single<U> flatMapItem(T item) {
        return Single.just(map(item));
    }

    public Single<List<U>> flatMapItems(List<T> items) {
        return Observable.fromIterable(items)
                .flatMap(item -> SingleFlatMapIterableObservable.just(map(item)))
                .toList();
    }

    protected abstract U map(T item);

}
