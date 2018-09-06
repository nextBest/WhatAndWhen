package com.nextbest.whatandwhen.ui.main.di;

import com.nextbest.whatandwhen.ui.main.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = {
        MainModule.class,
        MainFragmentProvider.class})
public interface MainComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}

