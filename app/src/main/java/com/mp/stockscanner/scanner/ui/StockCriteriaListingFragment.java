package com.mp.stockscanner.scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mp.stockscanner.R;
import com.mp.stockscanner.models.StockScanner;
import com.mp.stockscanner.models.Variable;
import com.mp.stockscanner.scanner.listener.SpanClickListener;

public class StockCriteriaListingFragment extends Fragment implements SpanClickListener {

    RecyclerView recyclerView;

    final static String KEY_STOCK_SCANNER = "stock_scanner";

    public static StockCriteriaListingFragment newInstance(StockScanner stockScanner) {
        StockCriteriaListingFragment fragment = new StockCriteriaListingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_STOCK_SCANNER, stockScanner);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.creteria_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_stockscanner);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CriteriaListingAdapter criteriaListingAdapter = new CriteriaListingAdapter(getArguments().getParcelable(KEY_STOCK_SCANNER));
        criteriaListingAdapter.setonClickListener(this);
        recyclerView.setAdapter(criteriaListingAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onSpanClick(Variable variable) {

    }

}
