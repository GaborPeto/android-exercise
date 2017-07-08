package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.presentation.util.IScheduler;
import com.gaborpeto.androidexercise.util.AppScheduler;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static IScheduler provideScheduler() {
        return new AppScheduler();
    }

}
