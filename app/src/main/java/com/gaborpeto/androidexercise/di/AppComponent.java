package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.App;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

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
