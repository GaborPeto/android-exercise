package com.gaborpeto.androidexercise.presentation.util;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestScheduler implements IScheduler {

    @Override
    public Scheduler main() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler background() {
        return Schedulers.trampoline();
    }
}
