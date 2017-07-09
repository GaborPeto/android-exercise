package com.gaborpeto.androidexercise.di.postdetails;

import android.app.Activity;

import com.gaborpeto.androidexercise.postdetails.PostDetailsActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PostDetailsActivityComponent.class)
public abstract class PostDetailsActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(PostDetailsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindPostDetailsActivityInjectorFactory(PostDetailsActivityComponent.Builder builder);

}
