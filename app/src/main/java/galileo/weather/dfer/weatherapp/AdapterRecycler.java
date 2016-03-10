package galileo.weather.dfer.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import galileo.weather.dfer.weatherapp.R;
import galileo.weather.dfer.weatherapp.WeatherInfo;

/**
 * Created by dfer on 8/03/16.
 */
public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private Context context;
    private HashMap<String, List<WeatherInfo>> countriesDataset;
    private List<WeatherInfo> currentDataset;
    private OnItemClickListener clickListener;

    public AdapterRecycler(Context context) {
        this.context = context;
        this.countriesDataset = new HashMap<String, List<WeatherInfo>>();
        this.currentDataset = new ArrayList<>();
        String[] countries = {"guatemala", "peru", "el salvador", "china", "rusia"};
        for (int i = 0; i < countries.length; i++) {
            fillWithElements(countries[i]);
        }
    }

    private void fillWithElements(String country) {
        List<WeatherInfo> dataset = new ArrayList<WeatherInfo>();
        String[] icons = {"01d","04n", "10n", "50n", "04n"};
        String[] descriptions = {"Despejado","Nublado", "Lluvioso", "Con Niebla", "Nubarrón"};
        String[] minTemp = {"12","11","15","17","8"};
        String[] maxTemp = {"22","21","19","20","28"};
        String[] temp = {"15","16","17","19","13"};
        String[] sunrise = {"6:20","6:10","6:00","5:40","6:13"};
        String[] sunset = {"7:10","7:00","6:45","7:30","5:50"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR,-1);
        Date oneDayBefore= cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date twoDayBefore= cal.getTime();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR,1);
        Date oneDayAfter= cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR,1);
        Date twoDayAfter= cal.getTime();
        DateFormat format=new SimpleDateFormat("EEEE");

        String[] days = {format.format(twoDayBefore), format.format(oneDayBefore), "TODAY!",
                format.format(oneDayAfter), format.format(twoDayAfter)};

        for (int i = 0; i < 5 ; i++) {
            WeatherInfo info = new WeatherInfo();
            int j = (int)(0 + (Math.random() * (5)));
            info.setSunrise(sunrise[j]);
            info.setSunset(sunset[j]);
            info.setDescription(descriptions[j]);
            info.setIconName(icons[j]);
            info.setTemp(temp[j]);
            info.setTempMin(minTemp[j]);
            info.setTempMax(maxTemp[j]);
            info.setDate(days[i]);
            dataset.add(info);
        }
        this.countriesDataset.put(country, dataset);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //@Override
    public int getItemCount() {
        return currentDataset.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherInfo element = currentDataset.get(position);
        holder.dayOfWeek.setText(element.getDate());
        String strTempMin = context.getString(R.string.main_message_min);
        strTempMin = String.format(strTempMin, element.getTempMin());
        holder.txtTempMin.setText(strTempMin);

        String strTempMax = context.getString(R.string.main_message_max);
        strTempMax = String.format(strTempMax, element.getTempMax());
        holder.txtTempMax.setText(strTempMax);

        holder.txtDescription.setText(element.getDescription());

        if (this.clickListener != null) {
            holder.setOnItemClickListener(element, this.clickListener);
        }
    }

    public void addElement(WeatherInfo element) {
        currentDataset.add(element);
        notifyDataSetChanged();
    }

    public void filterByCountry(String country) {
        List<WeatherInfo> l= countriesDataset.get(country.toLowerCase().trim());
        if (l == null) {
            currentDataset = new ArrayList<>();
        } else {
            currentDataset = l;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        currentDataset.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dayOfWeek) TextView dayOfWeek;
        @Bind(R.id.txtTempMin) TextView txtTempMin;
        @Bind(R.id.txtTempMax) TextView txtTempMax;
        @Bind(R.id.imgIcon) ImageView imgIcon;
        @Bind(R.id.txtDescription) TextView txtDescription;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final WeatherInfo element, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(element);
                }
            });
        }
    }
}
