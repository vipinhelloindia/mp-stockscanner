package com.mp.stockscanner.scanner.repo;

import androidx.annotation.MainThread;

import com.mp.stockscanner.base.Resource;

import io.reactivex.Observable;

public interface MpScannerRepository {
    @MainThread
    boolean shouldFetch();

    Observable<Resource> fetchMpScanStockData();
}
