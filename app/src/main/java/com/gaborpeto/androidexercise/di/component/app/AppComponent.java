package com.gaborpeto.androidexercise.di.component.app;

import com.gaborpeto.androidexercise.App;
import com.gaborpeto.androidexercise.di.module.api.ApiModule;
import com.gaborpeto.androidexercise.di.module.app.AppModule;
import com.gaborpeto.androidexercise.di.module.app.PostListActivityModule;
import com.gaborpeto.androidexercise.di.module.app.PostListFragmentModule;
import com.gaborpeto.androidexercise.di.module.domain.InteractorModule;
import com.gaborpeto.androidexercise.di.module.persistence.PersistenceModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        InteractorModule.class,
        PersistenceModule.class,
        ApiModule.class,
        PostListActivityModule.class,
        PostListFragmentModule.class
})
public interface AppComponent {

    void inject(App app);

}
