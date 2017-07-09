package com.gaborpeto.androidexercise.di;

import com.gaborpeto.androidexercise.postdetails.PostDetailsActivity;
import com.gaborpeto.androidexercise.postdetails.PostDetailsFragment;
import com.gaborpeto.androidexercise.postlist.PostListActivity;
import com.gaborpeto.androidexercise.postlist.PostListFragment;
import com.gaborpeto.androidexercise.presentation.util.IScheduler;
import com.gaborpeto.androidexercise.util.AppScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @Provides @Singleton
    static IScheduler provideScheduler() {
        return new AppScheduler();
    }

    @ContributesAndroidInjector
    abstract PostListActivity contributePostListActivityInjector();

    @ContributesAndroidInjector
    abstract PostListFragment contributePostListFragmentInjector();

    @ContributesAndroidInjector
    abstract PostDetailsActivity contributePostDetailsActivityInjector();

    @ContributesAndroidInjector
    abstract PostDetailsFragment contributePostDetailsFragmentInjector();

}
