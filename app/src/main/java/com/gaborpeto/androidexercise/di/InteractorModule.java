package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.domain.interactor.comment.GetCommentsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.comment.IGetCommentsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.GetPostInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.GetPostsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostsInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    IGetPostsInteractor provideGetPostsInteractor(GetPostsInteractor interactor) {
        return interactor;
    }

    @Provides
    IGetPostInteractor provideGetPostInteractor(GetPostInteractor interactor) {
        return interactor;
    }

    @Provides
    IGetCommentsInteractor provideGetCommentsInteractor(GetCommentsInteractor interactor) {
        return interactor;
    }

}
