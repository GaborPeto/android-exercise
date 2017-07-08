package com.gaborpeto.androidexercise.domain.interactor.comment;

import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

import io.reactivex.Flowable;

public interface IGetCommentsInteractor {

    Flowable<List<Comment>> getComments(int postId);

}
