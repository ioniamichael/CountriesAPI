package com.enjoyapps.countriesapi.utils;

import com.enjoyapps.countriesapi.pojo.Country;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArraySort {


    public void sortByNativeName(List<Country> mCountryList){
        Collections.sort(mCountryList, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getNativeName().compareToIgnoreCase(o2.getNativeName());
            }
        });
    }
}
