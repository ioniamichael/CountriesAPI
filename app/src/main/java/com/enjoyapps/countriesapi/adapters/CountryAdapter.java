package com.enjoyapps.countriesapi.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enjoyapps.countriesapi.R;
import com.enjoyapps.countriesapi.pojo.Country;
import com.enjoyapps.countriesapi.singletons.BorderCountriesSingleton;
import com.enjoyapps.countriesapi.utils.FragmentCommunication;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countries;
    private Context mContext;
    private Activity mActivity;
    private FragmentCommunication mCommunicator;
    private OnItemClickListener iOnItemClickListener;
    private final String TAG = "myDebug";

    public CountryAdapter(List<Country> countries, Context mContext, Activity mActivity) {
        this.countries = countries;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public CountryAdapter() {
    }

    public interface OnItemClickListener {
        void onItemClick(int position, List<String> countries);
    }

    public void setiOnItemClickListener(OnItemClickListener iOnItemClickListener) {
        this.iOnItemClickListener = iOnItemClickListener;
    }

    public void updateAdapter(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public void sortByArea() {
        Collections.sort(countries);
        updateAdapter(countries);
    }

    public void sortByNativeName() {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getNativeName().compareToIgnoreCase(o2.getNativeName());
            }
        });
        updateAdapter(countries);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(view, mCommunicator);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {
        holder.mTVCountryName.setText(countries.get(position).getName());
        holder.mTVCountryNativeName.setText(countries.get(position).getNativeName());

        GlideToVectorYou
                .init()
                .with(mContext)
                .load(Uri.parse(countries.get(position).getFlag()), holder.mCountryFlag);

        holder.mCountryFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (iOnItemClickListener != null) {
                    List<String> borderCountriesAlpha3 = new ArrayList<>();
                    for (int i = 0; i < countries.get(position).getBorders().size(); i++) {
                        try {
                            borderCountriesAlpha3.add(countries.get(position).getBorders().get(i));
                        } catch (Exception e) {

                        }
                    }
                    iOnItemClickListener.onItemClick(position, borderCountriesAlpha3);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCountryFlag;
        private TextView mTVCountryName, mTVCountryNativeName;
        private FragmentCommunication mCommunicator;

        public CountryViewHolder(@NonNull View itemView, FragmentCommunication mCommunicator) {
            super(itemView);
            mCountryFlag = itemView.findViewById(R.id.imgCountryFlag);
            mTVCountryName = itemView.findViewById(R.id.tvCountryName);
            mTVCountryNativeName = itemView.findViewById(R.id.tvCountryNativeName);
            this.mCommunicator = mCommunicator;


        }
    }
}
