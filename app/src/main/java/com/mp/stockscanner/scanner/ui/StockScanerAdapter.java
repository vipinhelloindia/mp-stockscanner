package com.mp.stockscanner.scanner.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mp.stockscanner.R;
import com.mp.stockscanner.models.StockScanner;
import com.mp.stockscanner.scanner.Utils;
import com.mp.stockscanner.scanner.listener.StockScannerListener;

import java.util.ArrayList;

public class StockScanerAdapter extends RecyclerView.Adapter<StockScanerAdapter.StockScannerVH> {
    ArrayList<StockScanner> myDataset;
    private StockScannerListener stockScannerListener;

    StockScanerAdapter(ArrayList<StockScanner> myDataset) {
        this.myDataset = myDataset;
    }

    void setonClickListener(StockScannerListener stockScannerListener) {
        this.stockScannerListener = stockScannerListener;
    }

    @Override
    public StockScannerVH onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock_scanner, parent, false);
        StockScannerVH vh = new StockScannerVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StockScannerVH holder, int position) {
        holder.setData(myDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public class StockScannerVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameView;
        private TextView tagView;

        public StockScannerVH(View v) {
            super(v);
            v.setOnClickListener(this);
            nameView = v.findViewById(R.id.tv_name);
            tagView = v.findViewById(R.id.tv_tag);
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getTagView() {
            return tagView;
        }

        public void setData(StockScanner stockScanner) {
            Utils.setText(getNameView(), stockScanner.getName());
            Utils.setText(getTagView(), stockScanner.getTag());
            Utils.setTextColor(getTagView(), stockScanner.getColor());
        }

        @Override
        public void onClick(View v) {
            if (stockScannerListener != null) {
                stockScannerListener.onStockScanClicked(myDataset.get(getAdapterPosition()));
            }
        }
    }
}