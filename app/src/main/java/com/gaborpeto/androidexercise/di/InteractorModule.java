package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.domain.interactor.comment.GetCommentsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.comment.IGetCommentsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.GetPostInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.GetPostsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostsInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides @Singleton
    IGetPostsInteractor provideGetPostsInteractor(GetPostsInteractor interactor) {
        return interactor;
    }

    @Provides @Singleton
    IGetPostInteractor provideGetPostInteractor(GetPostInteractor interactor) {
        return interactor;
    }

    @Provides @Singleton
    IGetCommentsInteractor provideGetCommentsInteractor(GetCommentsInteractor interactor) {
        return interactor;
    }

}
