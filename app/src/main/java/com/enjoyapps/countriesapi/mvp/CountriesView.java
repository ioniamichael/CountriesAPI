package com.enjoyapps.countriesapi.mvp;

import android.content.Context;

import com.enjoyapps.countriesapi.api.ApiClient;
import com.enjoyapps.countriesapi.api.ApiService;
import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesView {

    private final String TAG = "myDebug";


    private final CountryPresenter mCountryPresenter;
    private final Context mContext;
    private Call<List<Country>> call;

    public CountriesView(CountryPresenter mCountryPresenter, Context mContext, Call<List<Country>> call) {
        this.mCountryPresenter = mCountryPresenter;
        this.mContext = mContext;
        this.call = call;
    }

    public void getAllCountries() {
        mCountryPresenter.showPlaceHolder();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@androidx.annotation.NonNull Call<List<Country>> call, @androidx.annotation.NonNull Response<List<Country>> response) {
                    mCountryPresenter.removePlaceHolder();
                    mCountryPresenter.setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                mCountryPresenter.removePlaceHolder();
            }
        });
    }

}
