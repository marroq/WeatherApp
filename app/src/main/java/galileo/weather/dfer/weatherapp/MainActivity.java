package galileo.weather.dfer.weatherapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchCommunication {
    private final static String TAG = "MainActivity";
    DataFragment forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.forecast = (DataFragment) getSupportFragmentManager().findFragmentById(R.id.dataFragment);
    }

    @Override
    public void searchCity(String cityName) {
        forecast.searchCity(cityName);
    }
}
