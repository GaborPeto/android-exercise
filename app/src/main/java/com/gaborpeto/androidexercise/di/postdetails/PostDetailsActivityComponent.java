package com.gaborpeto.androidexercise.di.postdetails;

import com.gaborpeto.androidexercise.postdetails.PostDetailsActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface PostDetailsActivityComponent extends AndroidInjector<PostDetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PostDetailsActivity> {}

}
