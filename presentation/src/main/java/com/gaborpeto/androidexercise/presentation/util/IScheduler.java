package com.gaborpeto.androidexercise.presentation.util;

import io.reactivex.Scheduler;

public interface IScheduler {

    Scheduler main();

    Scheduler background();

}
