package com.mp.stockscanner.scanner.ui;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mp.stockscanner.R;
import com.mp.stockscanner.models.Criterium;
import com.mp.stockscanner.models.StockScanner;
import com.mp.stockscanner.scanner.Utils;
import com.mp.stockscanner.scanner.listener.SpanClickListener;

public class CriteriaListingAdapter extends RecyclerView.Adapter<CriteriaListingAdapter.CriteriaListingVH> {
    private StockScanner stockScanner;
    private SpanClickListener spanClickListener;

    CriteriaListingAdapter(StockScanner stockScanner) {
        this.stockScanner = stockScanner;
    }

    void setonClickListener(SpanClickListener spanClickListener) {
        this.spanClickListener = spanClickListener;
    }

    @Override
    public CriteriaListingVH onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock_scanner, parent, false);
        CriteriaListingVH vh = new CriteriaListingVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CriteriaListingVH holder, int position) {
        holder.setData(stockScanner.getCriteria().get(position));
    }

    @Override
    public int getItemCount() {
        return stockScanner.getCriteria().size();
    }

    public class CriteriaListingVH extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView tagView;

        public CriteriaListingVH(View v) {
            super(v);
            nameView = v.findViewById(R.id.tv_name);
            tagView = v.findViewById(R.id.tv_tag);
            tagView.setVisibility(View.GONE);
            nameView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        public TextView getNameView() {
            return nameView;
        }

        public void setData(Criterium criterium) {
            Utils.setSpanable(getNameView(), criterium, spanClickListener);
        }
    }
}