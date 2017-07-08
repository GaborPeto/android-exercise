package com.gaborpeto.androidexercise.domain.interactor.post;

import com.gaborpeto.androidexercise.domain.model.Post;

import io.reactivex.Flowable;

public interface IGetPostInteractor {

    Flowable<Post> getPost(int postId);

}
