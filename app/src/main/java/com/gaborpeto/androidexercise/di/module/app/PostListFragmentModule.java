package com.gaborpeto.androidexercise.di.module.app;

import android.app.Fragment;

import com.gaborpeto.androidexercise.di.component.app.PostListFragmentComponent;
import com.gaborpeto.androidexercise.postlist.PostListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PostListFragmentComponent.class)
public abstract class PostListFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(PostListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindPostListFragmentInjectorFactory(PostListFragmentComponent.Builder builder);

}
