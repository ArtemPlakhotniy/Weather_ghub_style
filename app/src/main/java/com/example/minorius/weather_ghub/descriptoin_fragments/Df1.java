package com.example.minorius.weather_ghub.descriptoin_fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.MainActivity;
import com.example.minorius.weather_ghub.R;
import com.example.minorius.weather_ghub.WeatherDb.WeatherDbase;
import com.example.minorius.weather_ghub.adapter.UpData;
import com.example.minorius.weather_ghub.adapter.WorkingAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class Df1 extends Fragment {

    TextView txtF1;
    TextView txtF2;
    TextView txtF3;
    TextView txtF4;
    TextView txtF5;

    ImageView iconImg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d1, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();

        txtF1 = (TextView) getView().findViewById(R.id.txtF1);
        txtF2 = (TextView) getView().findViewById(R.id.txtF2);
        txtF3 = (TextView) getView().findViewById(R.id.txtF3);
        txtF4 = (TextView) getView().findViewById(R.id.txtF4);
        txtF5 = (TextView) getView().findViewById(R.id.txtF5);

        iconImg = (ImageView) getView().findViewById(R.id.iconImg);

        Realm realm =  Realm.getInstance(getActivity());
        RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

        realm.beginTransaction();
        int count = results.size();
        if(count < 5){
            Toast.makeText(getActivity(), "You need ON internet", Toast.LENGTH_LONG).show();
        }else if (count >= 5){
            txtF1.setText(""+ results.get(count - 5).getTempInDb());
            txtF2.setText(""+ results.get(count - 5).getDataInDb());
            txtF3.setText(""+ results.get(count - 5).getDirectionInDb());
            txtF4.setText(""+ results.get(count - 5).getWindSpeedInDb());
            txtF5.setText(""+ results.get(count - 5).getHumidityInDb());

            String img = results.get(count - 5).getIconInDb();
            Picasso.with(getView().getContext())
                    .load(img)
                    .into(iconImg);

        }
        realm.commitTransaction();
    }
}
