package com.gaborpeto.androidexercise.domain.interactor.post;

import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

import io.reactivex.Flowable;

public interface IGetPostsInteractor {

    Flowable<List<Post>> getPosts();

}
