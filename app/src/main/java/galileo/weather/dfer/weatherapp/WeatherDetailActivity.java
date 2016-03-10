package galileo.weather.dfer.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import galileo.weather.dfer.weatherapp.R;

/**
 * Created by dfer on 10/03/16.
 */
public class WeatherDetailActivity extends AppCompatActivity {
    @Bind(R.id.txtDescription) TextView txtDescription;
    @Bind(R.id.imgIcon) ImageView imgIcon;
    @Bind(R.id.txtTemp) TextView txtTemp;
    @Bind(R.id.txtTempMin) TextView txtTempMin;
    @Bind(R.id.txtTempMax) TextView txtTempMax;
    @Bind(R.id.txtSunrise) TextView txtSunrise;
    @Bind(R.id.txtSunset) TextView txtSunset;

    public final static String MIN_KEY = "min";
    public final static String MAX_KEY = "max";
    public final static String ICON_KEY = "icon";
    public final static String TEMP_KEY = "temp";
    public final static String SUNSET_KEY = "sunset";
    public final static String SUNRISE_KEY = "sunrise";
    public final static String DESCRIPTION_KEY = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_detail_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String strTemp = getString(R.string.main_message_temp);
        strTemp = String.format(strTemp, intent.getStringExtra(TEMP_KEY));
        txtTemp.setText(strTemp);

        String strTempMin = getString(R.string.main_message_min);
        strTempMin = String.format(strTempMin, intent.getStringExtra(MIN_KEY));
        txtTempMin.setText(strTempMin);

        String strTempMax = getString(R.string.main_message_max);
        strTempMax = String.format(strTempMax, intent.getStringExtra(MAX_KEY));
        txtTempMax.setText(strTempMax);

        String strSunrises = getString(R.string.main_message_sunrise);
        strSunrises = String.format(strSunrises, intent.getStringExtra(SUNRISE_KEY));
        txtSunrise.setText(strSunrises);

        String strSunset = getString(R.string.main_message_sunset);
        strSunset = String.format(strSunset, intent.getStringExtra(SUNSET_KEY));
        txtSunset.setText(strSunset);

        txtDescription.setText(intent.getStringExtra(DESCRIPTION_KEY));

        String iconName = intent.getStringExtra(ICON_KEY);
        String iconUrl = "http://openweathermap.org/img/w/" + iconName + ".png";
        Glide.with(this).load(iconUrl).into(imgIcon);
    }
}
