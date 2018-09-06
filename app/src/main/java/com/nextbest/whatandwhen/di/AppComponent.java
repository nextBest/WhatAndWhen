package com.nextbest.whatandwhen.di;

import android.app.Application;

import com.nextbest.whatandwhen.app.WhatAndWhenApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(WhatAndWhenApp app);
}
