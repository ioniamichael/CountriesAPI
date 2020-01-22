package com.enjoyapps.countriesapi.singletons;

import com.enjoyapps.countriesapi.pojo.Country;

import java.util.ArrayList;
import java.util.List;

public class BorderCountriesSingleton {

    private static BorderCountriesSingleton instance = null;
    private List<Country> sBorderCountries = null;

    public static BorderCountriesSingleton getInstance() {
        if (instance == null){
            instance = new BorderCountriesSingleton();
        }
        return instance;
    }

    private BorderCountriesSingleton() {
        sBorderCountries = new ArrayList<>();
    }

    public List<Country> getsBorderCountries() {
        return this.sBorderCountries;
    }

    public void addToArray(Country country) {
        sBorderCountries.add(country);
    }
}
