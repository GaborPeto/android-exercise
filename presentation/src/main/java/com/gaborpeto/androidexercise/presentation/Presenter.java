package com.gaborpeto.androidexercise.presentation;

public abstract class Presenter<V> {

    protected V viewModel;

    public void setViewModel(V viewModel) {
        this.viewModel = viewModel;
    }

    public abstract void start();

    public abstract void finish();

}
