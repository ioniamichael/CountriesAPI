package com.enjoyapps.countriesapi.mvp;

import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

public interface CountryView {

    void setAdapter(List<Country> countries);
    void showPlaceHolder();
    void removePlaceHolder();
}
