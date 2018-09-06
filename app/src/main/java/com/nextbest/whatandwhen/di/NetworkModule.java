package com.nextbest.whatandwhen.di;

import android.app.Application;

import com.nextbest.whatandwhen.network.NetworkClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    NetworkClient provideNetrowkClient(Application application) {
        return new NetworkClient(application);
    }
}
