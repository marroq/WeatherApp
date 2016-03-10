package galileo.weather.dfer.weatherapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment  implements View.OnClickListener {
    @Bind(R.id.btnSubmit) Button btnSubmit;
    @Bind(R.id.inputCity) EditText inputCity;

    SearchCommunication channel;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            Log.d("onClick", "onClick: " + inputCity.getText().toString());
            if (this.channel != null && inputCity.getText().toString().length() > 0) {
                this.channel.searchCity(inputCity.getText().toString());
            }
        }
    }

    @Override
    public void  onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof  SearchCommunication) {
            this.channel = (SearchCommunication)context;
        }
    }

    @Override
    public void  onResume() {
        super.onResume();
        if (this.channel != null && inputCity.getText().toString().length() > 0) {
            this.channel.searchCity(inputCity.getText().toString());
        }
    }
}
