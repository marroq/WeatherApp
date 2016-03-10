package galileo.weather.dfer.weatherapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import galileo.weather.dfer.weatherapp.R;
import galileo.weather.dfer.weatherapp.WeatherDetailActivity;
import galileo.weather.dfer.weatherapp.AdapterRecycler;
import galileo.weather.dfer.weatherapp.OnItemClickListener;
import galileo.weather.dfer.weatherapp.WeatherInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment implements OnItemClickListener, ForecastFragmentListener {
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.cityTitle) TextView cityTitle;
    private AdapterRecycler adapter;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new AdapterRecycler (getActivity().getApplicationContext());
            adapter.setOnItemClickListener(this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addToList(WeatherInfo record) {
        adapter.addElement(record);
    }

    @Override
    public void onItemClick(WeatherInfo element) {
        Intent i = new Intent(getActivity(), WeatherDetailActivity.class);

        i.putExtra(WeatherDetailActivity.MIN_KEY, element.getTempMin());
        i.putExtra(WeatherDetailActivity.MAX_KEY, element.getTempMax());
        i.putExtra(WeatherDetailActivity.ICON_KEY, element.getIconName());
        i.putExtra(WeatherDetailActivity.TEMP_KEY, element.getTemp());
        i.putExtra(WeatherDetailActivity.SUNSET_KEY, element.getSunset());
        i.putExtra(WeatherDetailActivity.SUNRISE_KEY, element.getSunrise());

        startActivity(i);
    }

    public void searchCity(String cityName) {
        this.cityTitle.setText(cityName.toUpperCase());
        adapter.filterByCountry(cityName);
    }
}
