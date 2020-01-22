package com.enjoyapps.countriesapi.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.ui.fragments.CountryListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSortByName, mBtnSortByArea;
    private CountryListFragment countryListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openCountriesFragment();
        initViews();
        initClickListeners();

    }

    private void initClickListeners() {
        mBtnSortByName.setOnClickListener(this);
        mBtnSortByArea.setOnClickListener(this);
    }

    private void initViews() {
        mBtnSortByName = findViewById(R.id.btnSortByName);
        mBtnSortByArea = findViewById(R.id.btnSortByArea);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSortByArea:
                countryListFragment = (CountryListFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.main_container);
                try {
                    countryListFragment.sortByArea();
                } catch (Exception e) {

                }
                break;
            case R.id.btnSortByName:
                countryListFragment = (CountryListFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.main_container);
                try {
                    countryListFragment.sortByNativeName();
                } catch (Exception e) {

                }
                break;
        }
    }

    private void openCountriesFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new CountryListFragment())
                .commit();
    }
}
