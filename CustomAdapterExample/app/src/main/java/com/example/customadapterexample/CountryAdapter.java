package com.example.customadapterexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.List;

public class CountryAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    final private List<Country> countries;
    final private Context context;

    final int NUM_OF_CELL_TYPES = 2;
    final int REGULAR_COUNTRY_TYPE  = 0;
    final int POPULATION_COUNTRY_TYPE  = 1;

    public CountryAdapter(List<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Country country = countries.get(i);

        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (country.isHasPopulation())
                view = layoutInflater.inflate(R.layout.special_country_layout,viewGroup,false);
            else
                view = layoutInflater.inflate(R.layout.country_layout,viewGroup,false);
        }

        Button moveBtn = view.findViewById(R.id.move_btn);
        moveBtn.setTag(i);
        moveBtn.setOnClickListener(this);
        TextView countryTv =  view.findViewById(R.id.country_name);
        if (country.isHasPopulation()){
            TextView populationTv = view.findViewById(R.id.country_population);
            populationTv.setText(MessageFormat.format("{0}", country.getPopulation()));
        }
        CheckBox countryCb =  view.findViewById(R.id.country_checkbox);
        countryCb.setTag(i);
        countryCb.setOnCheckedChangeListener(this);
        ImageView countryIv =  view.findViewById(R.id.country_flag);
        countryTv.setText(country.getName());
        countryCb.setChecked(country.isGood());
        countryIv.setImageResource(country.getFlagResId());
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int position = (Integer)compoundButton.getTag();
        Country country = countries.get(position);
        country.setGood(b);
    }


    @Override
    public void onClick(View view) {
        int position = (Integer)view.getTag();
        Country country = countries.get(position);
        if (country.isGood())
            Toast.makeText(context, "Moving to "+country.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getViewTypeCount() {
        return NUM_OF_CELL_TYPES;
    }

    @Override
    public int getItemViewType(int position) {
        if (countries.get(position).isHasPopulation())
            return POPULATION_COUNTRY_TYPE;
        return REGULAR_COUNTRY_TYPE;

    }
}
