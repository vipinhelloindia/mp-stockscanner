package com.mp.stockscanner.scanner.repo;

import com.mp.stockscanner.base.Resource;
import com.mp.stockscanner.base.Status;
import com.mp.stockscanner.di.network.NetworkManager;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static com.mp.stockscanner.di.module.RxJavaModule.OBSERVER_ON;
import static com.mp.stockscanner.di.module.RxJavaModule.SUBSCRIBER_ON;
import static io.reactivex.Observable.just;

public class MpScannerRepositoryImpl implements MpScannerRepository {

    private NetworkManager networkManager;
    private MpScannerApiInterface mpScannerApiInterface;
    private Scheduler subscriberOn;
    private Scheduler observerOn;

    @Inject
    public MpScannerRepositoryImpl(MpScannerApiInterface mpScannerApiInterface,
                                   NetworkManager appNetworkManager,
                                   @Named(SUBSCRIBER_ON) Scheduler subscriberOn,
                                   @Named(OBSERVER_ON) Scheduler observerOn) {
        this.mpScannerApiInterface = mpScannerApiInterface;
        this.subscriberOn = subscriberOn;
        this.observerOn = observerOn;
        this.networkManager = appNetworkManager;
    }


    @Override
    public Observable<Resource> fetchMpScanStockData() {
        return mpScannerApiInterface.getStockScanner()
                .subscribeOn(subscriberOn)
                .observeOn(observerOn)
                .flatMap(responseBody -> {
                    if (responseBody != null) {
                        return getSuccess(responseBody);
                    }
                    return getError("");
                });
    }

    @Override
    public boolean shouldFetch() {
        return networkManager.isOnline();
    }

    private Observable<Resource> getError(String errorMessage) {
        return just(new Resource<>(Status.ERROR, null, errorMessage));
    }


    private Observable<Resource> getSuccess(Object data) {
        return just(new Resource<>(Status.SUCCESS, data, null));
    }
}
