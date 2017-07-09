package com.gaborpeto.androidexercise.persistence;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PersistenceConfig {

    public void init(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("exercise.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
