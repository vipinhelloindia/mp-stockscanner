package com.mp.stockscanner.di.app;

import android.app.Application;
import android.content.Context;

import com.mp.stockscanner.di.module.FactoryModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {FactoryModule.class})
public class AppModule {
    @Provides
    @ApplicationContext
    Context provideContext(Application context) {
        return context;
    }
}
