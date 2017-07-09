package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.presentation.TestPostDetailsViewPresenter;
import com.gaborpeto.androidexercise.presentation.TestPostListViewPresenter;
import com.gaborpeto.androidexercise.presentation.postdetails.IPostDetailsViewPresenter;
import com.gaborpeto.androidexercise.presentation.postlist.IPostListViewPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestPresentationModule {

    @Provides
    @Singleton
    static IPostListViewPresenter providePostListPresenter() {
        return new TestPostListViewPresenter();
    }

    @Provides
    @Singleton
    static IPostDetailsViewPresenter providePostDetailsPresenter() {
        return new TestPostDetailsViewPresenter();
    }

}
