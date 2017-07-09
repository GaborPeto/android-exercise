package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        InteractorModule.class,
        PersistenceModule.class,
        ApiModule.class
})
public interface AppComponent {

    void inject(App app);

}
