package com.gaborpeto.androidexercise.persistence.gateway;

import android.support.test.InstrumentationRegistry;

import com.gaborpeto.androidexercise.persistence.mapper.PersistablePostMapper;

import org.junit.After;
import org.junit.Before;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public abstract class BaseLocalGatewayTest {

    @Before
    public void setUp() throws Exception {
        Realm.init(InstrumentationRegistry.getContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("test.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @After
    public void tearDown() throws Exception {
        try(Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }
    }

}
