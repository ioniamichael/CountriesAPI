package com.enjoyapps.countriesapi.mvp;

import android.content.Context;

import com.enjoyapps.countriesapi.api.ApiClient;
import com.enjoyapps.countriesapi.api.ApiService;
import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryPresenter {

    private final String TAG = "myDebug";

    private final CountryView mCountryView;
    private final Context mContext;
    private Call<List<Country>> mCall;


    public CountryPresenter(CountryView mCountryView, Context mContext) {
        this.mCountryView = mCountryView;
        this.mContext = mContext;
    }

    public void getAllCountries() {
        mCountryView.showPlaceHolder();
        ApiService mApiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        mCall = mApiService.getAllCountries();
        mCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@androidx.annotation.NonNull Call<List<Country>> call, @androidx.annotation.NonNull Response<List<Country>> response) {
                    mCountryView.removePlaceHolder();
                    mCountryView.setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                mCountryView.removePlaceHolder();
            }
        });
    }

    public void disposeCallingAPI(){
        mCall.cancel();
    }

}
