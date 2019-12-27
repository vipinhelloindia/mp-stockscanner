package com.mp.stockscanner.scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mp.stockscanner.R;
import com.mp.stockscanner.base.BaseAppCompactFragment;
import com.mp.stockscanner.base.Resource;
import com.mp.stockscanner.base.Status;
import com.mp.stockscanner.models.StockScanner;
import com.mp.stockscanner.scanner.listener.StockScannerListener;
import com.mp.stockscanner.scanner.repo.MainViewModel;

import java.util.Objects;

import javax.inject.Inject;

public class StockListingFragment extends BaseAppCompactFragment implements StockScannerListener {
    @Inject
    MainViewModel mainViewModel;

    private RecyclerView recyclerView;

    public static StockListingFragment newInstance() {
        return new StockListingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_stockscanner);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel.getMutableLiveData().observe(this, arrayListResource -> {
            if (isSuccess(arrayListResource)) {
                StockScanerAdapter mAdapter = new StockScanerAdapter(arrayListResource.getData());
                mAdapter.setonClickListener(this);
                recyclerView.setAdapter(mAdapter);
            }
        });

        mainViewModel.getStockScan();
    }


    private boolean isSuccess(Resource resource) {
        return resource.getStatus() == Status.SUCCESS;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onStockScanClicked(StockScanner stockScanner) {

        FragmentManager mFragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.addToBackStack("criteria");
        ft.add(R.id.container, StockCriteriaListingFragment.newInstance(stockScanner));
        ft.commitAllowingStateLoss();
    }
}
