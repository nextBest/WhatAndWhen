package com.nextbest.whatandwhen.ui.main.events.di;

import com.nextbest.whatandwhen.ui.main.events.EventFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = EventModule.class)
public interface EventComponent extends AndroidInjector<EventFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<EventFragment> {
    }
}

