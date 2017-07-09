package com.gaborpeto.androidexercise.domain.interactor.comment;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalCommentGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemoteCommentGateway;
import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static java.util.concurrent.TimeUnit.*;

public class GetCommentsInteractor implements IGetCommentsInteractor {

    private ILocalCommentGateway localGateway;
    private IRemoteCommentGateway remoteGateway;

    @Inject public GetCommentsInteractor(
            ILocalCommentGateway localGateway,
            IRemoteCommentGateway remoteGateway) {
        this.localGateway = localGateway;
        this.remoteGateway = remoteGateway;
    }

    @Override
    public Flowable<List<Comment>> getComments(int postId) {
        return localComments(postId).concatWith(remoteComments(postId));
    }

    private Maybe<List<Comment>> remoteComments(int postId) {
        return remoteGateway
                .getComments(postId)
                .delaySubscription(100, MILLISECONDS)
                .doOnSuccess(comments -> localGateway.updateComments(comments))
                .toMaybe();
    }

    private Maybe<List<Comment>> localComments(int postId) {
        return localGateway.getComments(postId);
    }
}
