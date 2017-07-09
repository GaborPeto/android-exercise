package com.gaborpeto.androidexercise.di.postdetails;

import com.gaborpeto.androidexercise.postdetails.PostDetailsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface PostDetailsFragmentComponent extends AndroidInjector<PostDetailsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PostDetailsFragment> {}

}
