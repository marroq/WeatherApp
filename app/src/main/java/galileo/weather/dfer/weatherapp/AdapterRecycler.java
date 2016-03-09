package galileo.weather.dfer.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by dfer on 8/03/16.
 */
public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private String[] dataset;
    private OnItemClickListener clickListener;
    public AdapterRecycler(String[] dataset) {
        this.dataset = dataset;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //@Override
    public int getItemCount() {
        return dataset.length;
    }

    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String element = dataset[position];
        holder.txtContent.setText(element);
        if (this.clickListener != null) {
            holder.setOnItemClickListener(element, this.clickListener);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public TextView txtContent;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            txtContent = (TextView)view.findViewById(R.id.cityTitle);
        }
        public void setOnItemClickListener(final String element,
                                           final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(element);
                }
            });
        }
    }
}
