package com.nextbest.whatandwhen.ui.splash.di;

import com.nextbest.whatandwhen.ui.splash.SplashActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = SplashModule.class)
public interface SplashComponent extends AndroidInjector<SplashActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SplashActivity> {
    }
}

