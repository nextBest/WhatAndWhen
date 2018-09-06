package com.nextbest.whatandwhen.di;

import android.app.Activity;

import com.nextbest.whatandwhen.ui.main.MainActivity;
import com.nextbest.whatandwhen.ui.main.di.MainComponent;
import com.nextbest.whatandwhen.ui.splash.SplashActivity;
import com.nextbest.whatandwhen.ui.splash.di.SplashComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(SplashActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSplashActivity(SplashComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainComponent.Builder builder);
}
