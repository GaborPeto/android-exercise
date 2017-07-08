package com.gaborpeto.androidexercise;

import android.app.Activity;
import android.app.Application;
import com.gaborpeto.androidexercise.di.app.DaggerAppComponent;
import com.gaborpeto.androidexercise.di.PersistenceModule;

import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .persistenceModule(new PersistenceModule(this))
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
