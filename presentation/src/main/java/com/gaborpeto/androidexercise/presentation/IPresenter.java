package com.gaborpeto.androidexercise.presentation;

public interface IPresenter<V> {

    void setViewModel(V viewModel);

    void start();

    void finish();

}
