package com.enjoyapps.countriesapi.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.adapters.CountryAdapter;
import com.enjoyapps.countriesapi.mvp.CountryPresenter;
import com.enjoyapps.countriesapi.pojo.Country;
import com.enjoyapps.countriesapi.mvp.CountriesView;

import java.util.List;

public class CountryListFragment extends Fragment implements CountryPresenter {

    private View view;
    private RecyclerView mRVCountries;
    private CountryAdapter mCountryAdapter;
    private CountriesView mCountriesView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_country_list, container, false);
        initView(view);
        initClasses();
        initContentView();
        return view;
    }

    private void initClasses() {
        mCountriesView = new CountriesView(this, getContext());
    }

    private void initView(View view) {
        mRVCountries = view.findViewById(R.id.rvCountries);
    }

    private void initContentView() {
        mCountriesView.getAllCountries();

    }

    @Override
    public void generateDataList(List<Country> countries) {
        mCountryAdapter = new CountryAdapter(countries, getContext(), getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRVCountries.setLayoutManager(layoutManager);
        mRVCountries.setAdapter(mCountryAdapter);
    }

    public void sortByNativeName() {
        mCountryAdapter.sortByNativeName();
    }

    public void sortByArea() {
        mCountryAdapter.sortByArea();
    }

}

