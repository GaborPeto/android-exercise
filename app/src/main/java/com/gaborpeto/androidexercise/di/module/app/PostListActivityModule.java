package com.gaborpeto.androidexercise.di.module.app;

import android.app.Activity;

import com.gaborpeto.androidexercise.di.component.app.PostListActivityComponent;
import com.gaborpeto.androidexercise.postlist.PostListActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PostListActivityComponent.class)
public abstract class PostListActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(PostListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindPostListActivityInjectorFactory(PostListActivityComponent.Builder builder);
}

