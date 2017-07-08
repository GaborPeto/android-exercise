package com.gaborpeto.androidexercise.util;

import com.gaborpeto.androidexercise.presentation.util.IScheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AppScheduler implements IScheduler {

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler background() {
        return Schedulers.io();
    }
}
