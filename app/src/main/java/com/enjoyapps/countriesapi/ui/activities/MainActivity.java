package com.enjoyapps.countriesapi.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.ui.fragments.BorderCountriesFragment;
import com.enjoyapps.countriesapi.ui.fragments.CountryListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "myDebug";


    private Button mBtnSortByName, mBtnSortByArea;
    private Fragment mCurrentFragment;

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
                mCurrentFragment = getSupportFragmentManager()
                        .findFragmentById(R.id.main_container);
                try {
                    if (mCurrentFragment instanceof CountryListFragment) {
                        ((CountryListFragment) mCurrentFragment).sortByArea();
                    } else if (mCurrentFragment instanceof BorderCountriesFragment) {
                        ((BorderCountriesFragment) mCurrentFragment).sortByArea();
                    }
                } catch (Exception e) {
                    Log.d(TAG, "getBorderCountries: "+e.getMessage());
                }
                break;
            case R.id.btnSortByName:
                mCurrentFragment = getSupportFragmentManager()
                        .findFragmentById(R.id.main_container);
                try {
                    if (mCurrentFragment instanceof CountryListFragment) {
                        ((CountryListFragment) mCurrentFragment).sortByNativeName();
                    } else if (mCurrentFragment instanceof BorderCountriesFragment) {
                        ((BorderCountriesFragment) mCurrentFragment).sortByNativeName();
                    }
                } catch (Exception e) {
                    Log.d(TAG, "getBorderCountries: "+e.getMessage());
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
