package com.nextbest.whatandwhen.ui.splash.di;

import com.nextbest.whatandwhen.ui.splash.SplashActivity;
import com.nextbest.whatandwhen.ui.splash.SplashPresenter;
import com.nextbest.whatandwhen.ui.splash.SplashView;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    SplashView provideLoginView(SplashActivity splashActivity) {
        return splashActivity;
    }

    @Provides
    SplashPresenter provideLoginPresenter() {
        return new SplashPresenter();
    }
}
