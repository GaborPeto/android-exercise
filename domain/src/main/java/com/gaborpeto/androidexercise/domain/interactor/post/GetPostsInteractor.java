package com.gaborpeto.androidexercise.domain.interactor.post;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalPostGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;
import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static java.util.concurrent.TimeUnit.*;

public class GetPostsInteractor implements IGetPostsInteractor {

    private ILocalPostGateway localGateway;
    private IRemotePostGateway remoteGateway;

    @Inject public GetPostsInteractor(
            ILocalPostGateway localGateway,
            IRemotePostGateway remoteGateway) {
        this.localGateway = localGateway;
        this.remoteGateway = remoteGateway;
    }

    @Override
    public Flowable<List<Post>> getPosts() {
        return localPosts().concatWith(remotePosts());
    }

    private Maybe<List<Post>> remotePosts() {
        return remoteGateway
                .getPosts()
                .delaySubscription(100, MILLISECONDS)
                .doOnSuccess(posts -> localGateway.updatePosts(posts))
                .toMaybe();
    }

    private Maybe<List<Post>> localPosts() {
        return localGateway.getPosts();
    }
}
