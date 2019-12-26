package com.mp.stockscanner.di.app;


import com.mp.stockscanner.scanner.ui.StockScannerListingActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract StockScannerListingActivity contributeMainActivity();
}
