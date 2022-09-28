package com.example.recyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{
    private final List<Country> countries;
    private  MyCountryListener listener;

    interface MyCountryListener{
        void onCountryClicked(int position, View view);
        void onCountryLongClicked(int position, View view);
    }

    public void serListener(MyCountryListener listener){
        this.listener = listener;
    }

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView populationTv;
        ImageView flagIv;
        CheckBox goodCb;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.country_name);
            populationTv = itemView.findViewById(R.id.country_population);
            flagIv = itemView.findViewById(R.id.country_flag);
            goodCb = itemView.findViewById(R.id.country_checkbox);

            goodCb.setOnCheckedChangeListener((compoundButton, b) -> {
                Country country = countries.get(getAdapterPosition());
                country.setGood(b);
            });

            itemView.setOnClickListener(view -> {
                if (listener !=null){
                    listener.onCountryClicked(getAdapterPosition(),view);
                }
            });

            itemView.setOnLongClickListener(view -> {
                if (listener!=null){
                    listener.onCountryLongClicked(getAdapterPosition(),view);
                }
                return false;
            });
        }
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_cell,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.nameTv.setText(country.getName());
        holder.populationTv.setText(MessageFormat.format("{0}", country.getPopulation()));
        holder.flagIv.setImageResource(country.getFlagResId());
        holder.goodCb.setChecked(country.isGood());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

}
