package com.mp.stockscanner.di.module;

import com.mp.stockscanner.di.network.AppNetworkManager;
import com.mp.stockscanner.di.network.NetworkManager;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NetworkModule {
    @Binds
    abstract NetworkManager getAppNetworkManager(AppNetworkManager appNetworkManager);
}
