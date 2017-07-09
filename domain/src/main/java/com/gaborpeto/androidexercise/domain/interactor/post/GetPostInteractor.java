package com.gaborpeto.androidexercise.domain.interactor.post;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalPostGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;
import com.gaborpeto.androidexercise.domain.model.Post;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GetPostInteractor implements IGetPostInteractor {

    private ILocalPostGateway localGateway;
    private IRemotePostGateway remoteGateway;

    @Inject
    public GetPostInteractor(
            ILocalPostGateway localGateway,
            IRemotePostGateway remoteGateway) {
        this.localGateway = localGateway;
        this.remoteGateway = remoteGateway;
    }

    @Override
    public Flowable<Post> getPost(int postId) {
        return localPost(postId).concatWith(remotePost(postId));
    }

    private Maybe<Post> localPost(int postId) {
        return localGateway.getPost(postId);
    }

    private Maybe<Post> remotePost(int postId) {
        return remoteGateway
                .getPost(postId)
                .delaySubscription(100, MILLISECONDS)
                .doAfterSuccess(post -> localGateway.updatePost(post))
                .toMaybe();
    }
}
