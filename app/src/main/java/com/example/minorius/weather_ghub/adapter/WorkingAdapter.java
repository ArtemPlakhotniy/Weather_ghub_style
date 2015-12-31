package com.example.minorius.weather_ghub.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minorius.weather_ghub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkingAdapter extends ArrayAdapter<UpData> {

    private final Activity activity;
    public final ArrayList<UpData> weather_list;

    public WorkingAdapter(final Activity a, final int textViewResourceId, final ArrayList<UpData>weather_list){
        super(a, textViewResourceId, weather_list);
        this.weather_list = weather_list;
        activity = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_prototype, parent, false);
            holder = new ViewHolder();

            holder.txtDate = (TextView) v.findViewById(R.id.txtDate);
            holder.txtTemp = (TextView) v.findViewById(R.id.txtTemp);
            holder.txtWeather = (TextView) v.findViewById(R.id.txtWeather);
            holder.textView = (TextView) v.findViewById(R.id.textView);

            holder.iconProt = (ImageView) v.findViewById(R.id.iconProt);


            Typeface tf_w = Typeface.createFromAsset(getContext().getAssets(), "fonts/Weather.ttf");
            holder.txtWeather.setTypeface(tf_w);

            Typeface tf_d = Typeface.createFromAsset(getContext().getAssets(), "fonts/date.otf");
            holder.textView.setTypeface(tf_d);
            holder.txtTemp.setTypeface(tf_d);
            holder.txtDate.setTypeface(tf_d);

            v.setTag(holder);

        } else {
            holder = (ViewHolder)v.getTag();
        }

        UpData upData = weather_list.get(position);

        if(upData != null){
            holder.txtDate.setText(upData.getDate());
            holder.txtTemp.setText(upData.getTemp());
            holder.txtWeather.setText(upData.getP());
            //holder.test.setText(upData.getIcon());

            Picasso.with(getContext())
                    .load(upData.getIcon())
                    .into(holder.iconProt);

        }
        return v;
    }
    private static class ViewHolder{

        public TextView txtDate;
        public TextView txtTemp;
        public TextView txtWeather;
        public TextView textView;

        public ImageView iconProt;

    }
}