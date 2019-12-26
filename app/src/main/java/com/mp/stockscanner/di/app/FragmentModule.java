package com.mp.stockscanner.di.app;


import com.mp.stockscanner.di.module.MpScannerRepoModule;
import com.mp.stockscanner.scanner.ui.StockListingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector(modules = MpScannerRepoModule.class)
    abstract StockListingFragment contributeMainFragment();
}
