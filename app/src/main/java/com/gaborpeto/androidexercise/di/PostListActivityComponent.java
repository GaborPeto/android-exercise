package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.postlist.PostListActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface PostListActivityComponent extends AndroidInjector<PostListActivity> {

        @Subcomponent.Builder
        public abstract class Builder extends AndroidInjector.Builder<PostListActivity> {}

}
