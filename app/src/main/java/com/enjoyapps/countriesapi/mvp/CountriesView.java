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

    private CountryPresenter mCountryPresenter;
    private Context mContext;
    private Call<List<Country>> call;

    public CountriesView(CountryPresenter mCountryPresenter, Context mContext) {
        this.mCountryPresenter = mCountryPresenter;
        this.mContext = mContext;
    }


    public void getAllCountries(){
        mCountryPresenter.showPlaceHolder();
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        call = apiService.getAllCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
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
