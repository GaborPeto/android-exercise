package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.TestApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        TestPresentationModule.class
})
public interface TestAppComponent {

    void inject(TestApp app);

}
