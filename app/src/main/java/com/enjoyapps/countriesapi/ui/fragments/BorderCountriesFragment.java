package com.enjoyapps.countriesapi.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.adapters.BorderCountriesAdapter;
import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

public class BorderCountriesFragment extends Fragment {

    private View view;
    private RecyclerView mRvBorderCountries;
    private final List<Country> mBorderCountries;
    private BorderCountriesAdapter mBorderCountriesAdapter;

    public BorderCountriesFragment(List<Country> mBorderCountries) {
        this.mBorderCountries = mBorderCountries;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_border_countries, container, false);
        initView(view);
        Log.d("BorderCountriesFragment", "onCreateView: " + mBorderCountries.size());
        setAdapter();
        return view;
    }

    private void initView(View view) {
        mRvBorderCountries = view.findViewById(R.id.rvBorderCountries);
    }

    private void setAdapter() {
        mBorderCountriesAdapter = new BorderCountriesAdapter(mBorderCountries, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvBorderCountries.setLayoutManager(layoutManager);
        mRvBorderCountries.setAdapter(mBorderCountriesAdapter);
    }

    public void sortByNativeName() {
        mBorderCountriesAdapter.sortByNativeName();
    }

    public void sortByArea() {
        mBorderCountriesAdapter.sortByArea();
    }
}
