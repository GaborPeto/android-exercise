package com.gaborpeto.androidexercise.di.component.api;

import com.gaborpeto.androidexercise.di.module.api.ApiModule;

import dagger.Subcomponent;

@Subcomponent(modules = ApiModule.class)
public interface ApiComponent {

    @Subcomponent.Builder
    interface Builder {

        ApiComponent.Builder apiModule(ApiModule module);

        ApiComponent build();
    }

}
