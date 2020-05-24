package com.learntodroid.androidmvvmtutorial.ui.citieslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.androidmvvmtutorial.R;
import com.learntodroid.androidmvvmtutorial.data.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.CityRecyclerViewHolder> {
    private List<City> cities;
    private OnCityClickListener listener;

    public CityRecyclerViewAdapter(OnCityClickListener listener) {
        this.cities = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityRecyclerViewHolder holder, int position) {
        City city = cities.get(position);
        holder.bind(city, listener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    class CityRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView cityName;
        private TextView regionCountry;

        public CityRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            cityName = itemView.findViewById(R.id.item_city_name);
            regionCountry = itemView.findViewById(R.id.item_city_regioncountry);
        }

        public void bind(City city, OnCityClickListener listener) {
            cityName.setText(city.getCity());
            regionCountry.setText(city.getRegion() + ", " + city.getCountry());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(city);
                }
            });
        }
    }
}
