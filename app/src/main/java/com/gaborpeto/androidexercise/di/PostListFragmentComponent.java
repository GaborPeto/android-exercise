package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.postlist.PostListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface PostListFragmentComponent extends AndroidInjector<PostListFragment> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<PostListFragment> {}

}
