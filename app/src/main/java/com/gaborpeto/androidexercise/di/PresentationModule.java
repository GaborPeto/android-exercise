package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.presentation.postdetails.IPostDetailsViewPresenter;
import com.gaborpeto.androidexercise.presentation.postdetails.PostDetailsViewPresenter;
import com.gaborpeto.androidexercise.presentation.postlist.IPostListViewPresenter;
import com.gaborpeto.androidexercise.presentation.postlist.PostListViewPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    IPostListViewPresenter providePostListViewPresenter(PostListViewPresenter presenter) {
        return presenter;
    }

    @Provides
    IPostDetailsViewPresenter providePostDetailsViewPresenter(PostDetailsViewPresenter presenter) {
        return presenter;
    }

}
