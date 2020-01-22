package com.enjoyapps.countriesapi.api;

import com.enjoyapps.countriesapi.modal.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("all")
    Call<List<Country>> getAllCountries();

}
