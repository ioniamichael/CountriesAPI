package com.enjoyapps.countriesapi.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.adapters.CountryAdapter;
import com.enjoyapps.countriesapi.mvp.CountriesView;
import com.enjoyapps.countriesapi.mvp.CountryPresenter;
import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

public class CountryListFragment extends Fragment implements CountryPresenter {

    private final String TAG = "myDebug";


    private View view;
    private RecyclerView mRVCountries;
    private CountryAdapter mCountryAdapter;
    private CountriesView mCountriesView;
    private View incPlaceHolder;
    private LottieAnimationView mLavPlaceHolder;

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
        incPlaceHolder = view.findViewById(R.id.incPlaceHolder);
        mLavPlaceHolder = view.findViewById(R.id.lavPlaceHolder);
    }

    private void initContentView() {
        mCountriesView.getAllCountries();
    }

    @Override
    public void setAdapter(List<Country> countries) {
        mCountryAdapter = new CountryAdapter(countries, getContext(), getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRVCountries.setLayoutManager(layoutManager);
        mRVCountries.setAdapter(mCountryAdapter);

        mCountryAdapter.setiOnItemClickListener(new CountryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, List<Country> borderCountries) {
                Log.d(TAG, "onItemClick: " + position + " " + borderCountries.size());
                if (borderCountries.size() > 0) {
                    if (getFragmentManager() != null) {
                        getFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main_container, new BorderCountriesFragment(borderCountries))
                                .commit();
                    }
                } else {
                    Toast.makeText(getContext(), "The country you have selected does not have border countries", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showPlaceHolder() {
        mLavPlaceHolder.setVisibility(View.VISIBLE);
        mLavPlaceHolder.playAnimation();
    }

    @Override
    public void removePlaceHolder() {
        mLavPlaceHolder.pauseAnimation();
        mLavPlaceHolder.setVisibility(View.GONE);
    }

    public void sortByNativeName() {
        mCountryAdapter.sortByNativeName();
    }

    public void sortByArea() {
        mCountryAdapter.sortByArea();
    }
}

