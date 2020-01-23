package com.enjoyapps.countriesapi.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.pojo.Country;
import com.enjoyapps.countriesapi.utils.ArraySort;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BorderCountriesAdapter extends RecyclerView.Adapter<BorderCountriesAdapter.BorderCountriesViewHolder> {

    private List<Country> mBorderCountries;
    private Context mContext;
    private ArraySort sort = new ArraySort();

    public BorderCountriesAdapter(List<Country> mBorderCountries, Context mContext) {
        this.mBorderCountries = mBorderCountries;
        this.mContext = mContext;
    }

    public void updateAdapter(List<Country> countries) {
        this.mBorderCountries = countries;
        notifyDataSetChanged();
    }

    public void sortByArea() {
        Collections.sort(mBorderCountries);
        updateAdapter(mBorderCountries);
    }

    public void sortByNativeName() {
        sort.sortByNativeName(mBorderCountries);
        updateAdapter(mBorderCountries);
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
        holder.mTVCountryName.setText(mBorderCountries.get(position).getName());
        holder.mTVCountryNativeName.setText(mBorderCountries.get(position).getNativeName());

        GlideToVectorYou
                .init()
                .with(mContext)
                .load(Uri.parse(mBorderCountries.get(position).getFlag()), holder.mCountryFlag);
    }

    @Override
    public int getItemCount() {
        return mBorderCountries.size();
    }

    class BorderCountriesViewHolder extends RecyclerView.ViewHolder {

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
