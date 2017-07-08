package com.gaborpeto.androidexercise.di.component.domain;

import com.gaborpeto.androidexercise.di.module.domain.InteractorModule;

import dagger.Subcomponent;

@Subcomponent(modules = InteractorModule.class)
public interface InteractorComponent {

    @Subcomponent.Builder
    interface Builder {

        Builder interactorModule(InteractorModule module);

        InteractorComponent build();
    }

}
