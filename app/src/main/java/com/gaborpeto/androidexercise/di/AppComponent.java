package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.App;
import com.gaborpeto.androidexercise.di.postdetails.PostDetailsActivityModule;
import com.gaborpeto.androidexercise.di.postdetails.PostDetailsFragmentModule;
import com.gaborpeto.androidexercise.di.postlist.PostListActivityModule;
import com.gaborpeto.androidexercise.di.postlist.PostListFragmentModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        InteractorModule.class,
        PersistenceModule.class,
        ApiModule.class,
        PostListActivityModule.class,
        PostListFragmentModule.class,
        PostDetailsActivityModule.class,
        PostDetailsFragmentModule.class
})
public interface AppComponent {

    void inject(App app);

}
