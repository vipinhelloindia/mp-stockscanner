package com.mp.stockscanner.scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mp.stockscanner.R;
import com.mp.stockscanner.models.Variable;

import java.util.ArrayList;

public class VariableListingFragment extends Fragment {

    private ListView listView;

    final static String KEY_STOCK_SCANNER = "variable";

    public static VariableListingFragment newInstance(Variable variable) {
        VariableListingFragment fragment = new VariableListingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_STOCK_SCANNER, variable);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.variable_fragment, container, false);
        listView = view.findViewById(R.id.lv_variable);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Variable variable = getArguments().getParcelable(KEY_STOCK_SCANNER);
        ArrayAdapter itemsAdapter = null;
        if (variable != null) {
            if (variable.getValues() != null && variable.getValues().size() > 0) {
                itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_variable_layout, android.R.id.text1, variable.getValues());
            } else {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(variable.getDefaultValue().toString());
                itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_variable_layout, android.R.id.text1, strings);

            }
            listView.setAdapter(itemsAdapter);
        }


    }


}
