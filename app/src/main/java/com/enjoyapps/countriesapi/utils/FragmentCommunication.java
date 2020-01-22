package com.enjoyapps.countriesapi.utils;

import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

public interface FragmentCommunication {
    void respond(int position, List<String> countries);
}