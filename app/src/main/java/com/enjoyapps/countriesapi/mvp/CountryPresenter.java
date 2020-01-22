package com.enjoyapps.countriesapi.mvp;

import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

public interface CountryPresenter {

    void generateDataList(List<Country> countries);
}
