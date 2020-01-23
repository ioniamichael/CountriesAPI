package com.enjoyapps.countriesapi.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.pojo.Country;
import com.enjoyapps.countriesapi.utils.ArraySort;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> mCountries;
    private Context mContext;
    private Activity mActivity;
    private OnItemClickListener iOnItemClickListener;
    private ArraySort sort = new ArraySort();

    public CountryAdapter(List<Country> countries, Context mContext, Activity mActivity) {
        this.mCountries = countries;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, List<Country> countries);
    }

    public void setiOnItemClickListener(OnItemClickListener iOnItemClickListener) {
        this.iOnItemClickListener = iOnItemClickListener;
    }

    public void updateAdapter(List<Country> countries) {
        this.mCountries = countries;
        notifyDataSetChanged();
    }

    public void sortByArea() {
        Collections.sort(mCountries);
        updateAdapter(mCountries);
    }

    public void sortByNativeName() {
        sort.sortByNativeName(mCountries);
        updateAdapter(mCountries);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {
        holder.mTVCountryName.setText(mCountries.get(position).getName());
        holder.mTVCountryNativeName.setText(mCountries.get(position).getNativeName());

        GlideToVectorYou
                .init()
                .with(mContext)
                .load(Uri.parse(mCountries.get(position).getFlag()), holder.mCountryFlag);

        holder.mCountry_item_root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (iOnItemClickListener != null) {
                    iOnItemClickListener.onItemClick(position, getBorderCountries(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCountryFlag;
        private TextView mTVCountryName, mTVCountryNativeName;
        private LinearLayout mCountry_item_root_view;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            mCountryFlag = itemView.findViewById(R.id.imgCountryFlag);
            mTVCountryName = itemView.findViewById(R.id.tvCountryName);
            mTVCountryNativeName = itemView.findViewById(R.id.tvCountryNativeName);
            mCountry_item_root_view = itemView.findViewById(R.id.country_item_root_view);
        }
    }

    private List<Country> getBorderCountries(int position) {
        List<String> borderCountriesAlpha3 = new ArrayList<>();
        List<Country> borderCountries = new ArrayList<>();

        for (int i = 0; i < mCountries.get(position).getBorders().size(); i++) {
            try {
                borderCountriesAlpha3.add(mCountries.get(position).getBorders().get(i));
            } catch (Exception e) {

            }
        }

        for (int i = 0; i < mCountries.size(); i++) {
            for (int j = 0; j < borderCountriesAlpha3.size(); j++) {
                if (mCountries.get(i).getAlpha3Code().contains(borderCountriesAlpha3.get(j))) {
                    borderCountries.add(mCountries.get(i));
                }
            }
        }
        return borderCountries;
    }
}
