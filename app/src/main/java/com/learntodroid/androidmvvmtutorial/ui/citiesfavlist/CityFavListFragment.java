package com.learntodroid.androidmvvmtutorial.ui.citiesfavlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.androidmvvmtutorial.R;
import com.learntodroid.androidmvvmtutorial.data.model.City;
import com.learntodroid.androidmvvmtutorial.ui.citieslist.CityListViewModel;
import com.learntodroid.androidmvvmtutorial.ui.citieslist.CityRecyclerViewAdapter;
import com.learntodroid.androidmvvmtutorial.ui.citieslist.OnCityClickListener;

import java.util.List;

import static com.learntodroid.androidmvvmtutorial.ui.citieslist.CityListFragment.CITY_WIKI_ID;

public class CityFavListFragment extends Fragment implements OnCityClickListener {
    private CityRecyclerViewAdapter cityRecyclerViewAdapter;
    private CityFavListViewModel cityFavListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cityRecyclerViewAdapter = new CityRecyclerViewAdapter(this);
        cityFavListViewModel = ViewModelProviders.of(this).get(CityFavListViewModel.class);
        cityFavListViewModel.getFavouriteCitiesLiveData().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                if (cities != null) {
                    cityRecyclerViewAdapter.setCities(cities);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cityfavlist, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_cityfavlist_cityresults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cityRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onClick(City city) {
        Bundle bundle = new Bundle();
        bundle.putString(CITY_WIKI_ID, city.getWikiDataId());
        Navigation.findNavController(getView()).navigate(R.id.action_cityFavListFragment_to_cityDetailFragment, bundle);
    }
}
