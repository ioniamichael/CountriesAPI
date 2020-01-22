package com.enjoyapps.countriesapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.pojo.Country;

import java.util.List;

public class BorderCountriesAdapter extends RecyclerView.Adapter<BorderCountriesAdapter.BorderCountriesViewHolder>{

    private List<Country> mBorderCountries;
    private Context mContext;

    public BorderCountriesAdapter(List<Country> mBorderCountries, Context mContext) {
        this.mBorderCountries = mBorderCountries;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BorderCountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new BorderCountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BorderCountriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBorderCountries.size();
    }

    class BorderCountriesViewHolder extends RecyclerView.ViewHolder{

        private ImageView mCountryFlag;
        private TextView mTVCountryName, mTVCountryNativeName;

        public BorderCountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            mCountryFlag = itemView.findViewById(R.id.imgCountryFlag);
            mTVCountryName = itemView.findViewById(R.id.tvCountryName);
            mTVCountryNativeName = itemView.findViewById(R.id.tvCountryNativeName);
        }
    }
}
