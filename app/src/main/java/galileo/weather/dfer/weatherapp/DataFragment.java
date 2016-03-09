package galileo.weather.dfer.weatherapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {
    RecyclerView recyclerView;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_data, container, false);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String[] dataset = new String[]{"uno", "dos", "tres", "cuatro", "cinco"};
        AdapterRecycler adapter = new AdapterRecycler(dataset);
        //adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
