package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.api.client.ApiClient;
import com.gaborpeto.androidexercise.api.client.ApiClientFactory;
import com.gaborpeto.androidexercise.api.gateway.RemoteCommentGateway;
import com.gaborpeto.androidexercise.api.gateway.RemotePostGateway;
import com.gaborpeto.androidexercise.api.mapper.RemoteCommentMapper;
import com.gaborpeto.androidexercise.api.mapper.RemotePostMapper;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemoteCommentGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    ApiClient provideApiClient() {
        return new ApiClientFactory().create();
    }

    @Provides
    IRemotePostGateway providePostGateway(ApiClient client) {
        return new RemotePostGateway(client, new RemotePostMapper());
    }

    @Provides
    IRemoteCommentGateway provideCommentGateway(ApiClient client) {
        return new RemoteCommentGateway(client, new RemoteCommentMapper());
    }

}
