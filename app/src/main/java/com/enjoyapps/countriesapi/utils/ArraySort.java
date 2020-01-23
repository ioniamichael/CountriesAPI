package com.enjoyapps.countriesapi.utils;

import com.enjoyapps.countriesapi.pojo.Country;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArraySort {

    private boolean isSortedByNameAscending = false;
    private boolean isSortedByAreaAscending = false;

    public void sortByNativeName(List<Country> mCountryList) {
        if (isSortedByNameAscending) {
            Collections.reverse(mCountryList);
            isSortedByNameAscending = false;
        } else {
            Collections.sort(mCountryList, new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });
            isSortedByNameAscending = true;
        }
    }

    public void sortByArea(List<Country> mCountryList) {
        if (isSortedByAreaAscending) {
            Collections.reverse(mCountryList);
            isSortedByAreaAscending = false;
        } else {
            Collections.sort(mCountryList, new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    return Double.compare(o1.getArea(), o2.getArea());
                }
            });
            isSortedByAreaAscending = true;
        }
    }


}
