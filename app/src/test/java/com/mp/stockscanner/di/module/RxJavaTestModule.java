package com.mp.stockscanner.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


@Module
public class RxJavaTestModule {
    final String SUBSCRIBER_ON = "SubscribeOn";
    final String OBSERVER_ON = "ObserverOn";

    @Provides
    @Named(SUBSCRIBER_ON)
    Scheduler provideSubscriberOn() {
        return Schedulers.trampoline();
    }

    @Provides
    @Named(OBSERVER_ON)
    Scheduler provideObserverOn() {
        return Schedulers.trampoline();
    }

}