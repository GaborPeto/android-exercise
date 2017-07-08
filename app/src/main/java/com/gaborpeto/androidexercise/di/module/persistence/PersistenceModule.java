package com.gaborpeto.androidexercise.di.module.persistence;

import android.content.Context;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalCommentGateway;
import com.gaborpeto.androidexercise.domain.gateway.local.ILocalPostGateway;
import com.gaborpeto.androidexercise.persistence.gateway.LocalCommentGateway;
import com.gaborpeto.androidexercise.persistence.gateway.LocalPostGateway;
import com.gaborpeto.androidexercise.persistence.mapper.PersistableCommentMapper;
import com.gaborpeto.androidexercise.persistence.mapper.PersistablePostMapper;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class PersistenceModule {

    public PersistenceModule(Context context) {
        Realm.init(context);
    }

    @Provides
    ILocalPostGateway provideLocalPostGateway() {
        return new LocalPostGateway(new PersistablePostMapper());
    }

    @Provides
    ILocalCommentGateway provideLocalCommentGateway() {
        return new LocalCommentGateway(new PersistableCommentMapper());
    }

}
