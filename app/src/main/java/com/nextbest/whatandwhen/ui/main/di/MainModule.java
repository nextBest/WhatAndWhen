package com.nextbest.whatandwhen.ui.main.di;

import com.nextbest.whatandwhen.ui.main.MainActivity;
import com.nextbest.whatandwhen.ui.main.MainPresenter;
import com.nextbest.whatandwhen.ui.main.MainView;
import com.nextbest.whatandwhen.ui.main.events.di.EventComponent;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {
        EventComponent.class,
})
public class MainModule {

    @Provides
    MainView provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
