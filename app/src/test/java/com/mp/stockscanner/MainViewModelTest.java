package com.mp.stockscanner;

import androidx.lifecycle.Observer;

import com.mp.stockscanner.base.BaseTest;
import com.mp.stockscanner.base.Resource;
import com.mp.stockscanner.di.component.DaggerTestComponent;
import com.mp.stockscanner.di.component.TestComponent;
import com.mp.stockscanner.models.StockScanner;
import com.mp.stockscanner.scanner.repo.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class MainViewModelTest extends BaseTest {

    private TestComponent testComponent;

    @Inject
    MainViewModel mainViewModel;

    @Mock
    Observer<Resource<ArrayList<StockScanner>>> liveDataObserver;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        testComponent = DaggerTestComponent.builder().build();
        testComponent.inject(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetScanData() {
        mainViewModel.getMutableLiveData().observeForever(liveDataObserver);
        mainViewModel.getStockScan();
        verify(liveDataObserver).onChanged(any());

    }

}
