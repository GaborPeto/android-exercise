package com.gaborpeto.androidexercise.di.postlist;

import android.app.Fragment;

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
