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
public class Df5 extends Fragment {

    TextView txtF1_5;
    TextView txtF2_5;
    TextView txtF3_5;
    TextView txtF4_5;
    TextView txtF5_5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d5, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        txtF1_5 = (TextView) getView().findViewById(R.id.txtF1_5);
        txtF2_5 = (TextView) getView().findViewById(R.id.txtF2_5);
        txtF3_5 = (TextView) getView().findViewById(R.id.txtF3_5);
        txtF4_5 = (TextView) getView().findViewById(R.id.txtF4_5);
        txtF5_5 = (TextView) getView().findViewById(R.id.txtF5_5);

        Realm realm =  Realm.getInstance(getActivity());
        RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

        realm.beginTransaction();
        int count = results.size();
        if(count < 5){
            Toast.makeText(getActivity(), "You need ON internet", Toast.LENGTH_LONG).show();
        }else if (count >= 5){
            txtF1_5.setText(""+ results.get(count - 1).getTempInDb());
            txtF2_5.setText(""+ results.get(count - 1).getDataInDb());
            txtF3_5.setText(""+ results.get(count - 1).getDirectionInDb());
            txtF4_5.setText(""+ results.get(count - 1).getWindSpeedInDb());
            txtF5_5.setText(""+ results.get(count - 1).getHumidityInDb());
        }
        realm.commitTransaction();
    }
}
