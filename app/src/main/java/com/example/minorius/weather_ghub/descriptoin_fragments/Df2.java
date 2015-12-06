package com.example.minorius.weather_ghub.descriptoin_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.R;
import com.example.minorius.weather_ghub.WeatherDb.WeatherDbase;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Minorius on 30.11.2015.
 */
public class Df2 extends Fragment {

    TextView txtF1_2;
    TextView txtF2_2;
    TextView txtF3_2;
    TextView txtF4_2;
    TextView txtF5_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        txtF1_2 = (TextView) getView().findViewById(R.id.txtF1_2);
        txtF2_2 = (TextView) getView().findViewById(R.id.txtF2_2);
        txtF3_2 = (TextView) getView().findViewById(R.id.txtF3_2);
        txtF4_2 = (TextView) getView().findViewById(R.id.txtF4_2);
        txtF5_2 = (TextView) getView().findViewById(R.id.txtF5_2);

        Realm realm =  Realm.getInstance(getActivity());
        RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

        realm.beginTransaction();
        int count = results.size();
        if(count < 5){
            Toast.makeText(getActivity(), "You need ON internet", Toast.LENGTH_LONG).show();
        }else if (count >= 5){
            txtF1_2.setText(""+ results.get(count - 4).getTempInDb());
            txtF2_2.setText(""+ results.get(count - 4).getDataInDb());
            txtF3_2.setText(""+ results.get(count - 4).getDirectionInDb());
            txtF4_2.setText(""+ results.get(count - 4).getWindSpeedInDb());
            txtF5_2.setText(""+ results.get(count - 4).getHumidityInDb());
        }
        realm.commitTransaction();
    }
}

