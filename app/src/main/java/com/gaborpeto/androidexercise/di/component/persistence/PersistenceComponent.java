package com.gaborpeto.androidexercise.di.component.persistence;

import com.gaborpeto.androidexercise.di.module.persistence.PersistenceModule;

import dagger.Subcomponent;

@Subcomponent(modules = PersistenceModule.class)
public interface PersistenceComponent {

    @Subcomponent.Builder
    interface Builder {

        PersistenceComponent.Builder persistenceModule(PersistenceModule module);

        PersistenceComponent build();
    }

}
