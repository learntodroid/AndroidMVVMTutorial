package com.learntodroid.androidmvvmtutorial.ui.citieslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

import java.util.List;

public class CityListFragment extends Fragment implements OnCityClickListener {
    public static final String CITY_WIKI_ID = "CITY_WIKI_ID";

    private CityListViewModel cityViewModel;
    private CityRecyclerViewAdapter cityRecyclerViewAdapter;
    private EditText searchEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cityRecyclerViewAdapter = new CityRecyclerViewAdapter(this);
        cityViewModel = ViewModelProviders.of(this).get(CityListViewModel.class);
        cityViewModel.getCitiesLiveData().observe(this, new Observer<List<City>>() {
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
        View view = inflater.inflate(R.layout.fragment_searchcities, container, false);

        searchEditText = view.findViewById(R.id.fragment_searchcities_search);

        view.findViewById(R.id.fragment_searchcities_getcities).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityViewModel.getCities(searchEditText.getText().toString());
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.fragment_searchcities_cityresults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cityRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onClick(City city) {
        Bundle bundle = new Bundle();
        bundle.putString(CITY_WIKI_ID, city.getWikiDataId());
        Navigation.findNavController(getView()).navigate(R.id.action_cityFragment_to_cityDetailFragment, bundle);
    }
}
