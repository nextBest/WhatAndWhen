package com.nextbest.whatandwhen.di;

import android.app.Application;
import android.content.Context;

import com.nextbest.whatandwhen.ui.main.di.MainComponent;
import com.nextbest.whatandwhen.ui.splash.di.SplashComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {
        SplashComponent.class,
        MainComponent.class,
})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


}
