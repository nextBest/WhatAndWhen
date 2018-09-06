package com.nextbest.whatandwhen.ui.main.events.di;

import com.nextbest.whatandwhen.ui.main.events.EventFragment;
import com.nextbest.whatandwhen.ui.main.events.EventPresenter;
import com.nextbest.whatandwhen.ui.main.events.EventView;

import dagger.Module;
import dagger.Provides;

@Module
public class EventModule {

    @Provides
    EventView provideEventView(EventFragment eventFragment) {
        return eventFragment;
    }

    @Provides
    EventPresenter provideExplorePresenter() {
        return new EventPresenter();
    }

}
