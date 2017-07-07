package com.gaborpeto.androidexercise.persistence.mapper;

import com.gaborpeto.androidexercise.domain.mapper.Mapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleFlatMapIterableObservable;

import static java.util.stream.Collectors.*;

public abstract class PersistableMapper<T, U> extends Mapper<T, U> {

    public List<U> mapItems(List<T> items) {
        return items.stream().map(this::map).collect(toList());
    }

    public Single<List<T>> inverseFlatMapItems(List<U> items) {
        return Observable.fromIterable(items)
                .flatMap(item -> SingleFlatMapIterableObservable.just(inverseMap(item)))
                .toList();
    }

    public Single<T> inverseFlatMapItem(U item) {
        return Single.just(inverseMap(item));
    }

    protected abstract T inverseMap(U item);

}
