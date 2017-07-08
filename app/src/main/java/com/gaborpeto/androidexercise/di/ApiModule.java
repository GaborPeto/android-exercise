package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.api.client.ApiClient;
import com.gaborpeto.androidexercise.api.gateway.RemoteCommentGateway;
import com.gaborpeto.androidexercise.api.gateway.RemotePostGateway;
import com.gaborpeto.androidexercise.api.mapper.RemoteCommentMapper;
import com.gaborpeto.androidexercise.api.mapper.RemotePostMapper;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemoteCommentGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    ApiClient provideApiClient() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.ENDPOINT)
                .client(new OkHttpClient().newBuilder().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiClient.class);
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
