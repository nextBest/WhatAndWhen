package com.nextbest.whatandwhen.ui.main.di;

import android.support.v4.app.Fragment;

import com.nextbest.whatandwhen.ui.main.events.EventFragment;
import com.nextbest.whatandwhen.ui.main.events.di.EventComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainFragmentProvider {


    @Binds
    @IntoMap
    @FragmentKey(EventFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideEventFragmentFactory(EventComponent.Builder builder);
}
