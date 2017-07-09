package com.gaborpeto.androidexercise.di.postdetails;

import android.app.Fragment;

import com.gaborpeto.androidexercise.postdetails.PostDetailsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PostDetailsFragmentComponent.class)
public abstract class PostDetailsFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(PostDetailsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindPostDetailsFragmentInjectorFactory(PostDetailsFragmentComponent.Builder builder);

}
