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
public class Df3 extends Fragment {

    TextView txtF1_3;
    TextView txtF2_3;
    TextView txtF3_3;
    TextView txtF4_3;
    TextView txtF5_3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d3, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        txtF1_3 = (TextView) getView().findViewById(R.id.txtF1_3);
        txtF2_3 = (TextView) getView().findViewById(R.id.txtF2_3);
        txtF3_3 = (TextView) getView().findViewById(R.id.txtF3_3);
        txtF4_3 = (TextView) getView().findViewById(R.id.txtF4_3);
        txtF5_3 = (TextView) getView().findViewById(R.id.txtF5_3);

        Realm realm =  Realm.getInstance(getActivity());
        RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

        realm.beginTransaction();
        int count = results.size();
        if(count < 5){
            Toast.makeText(getActivity(), "You need ON internet", Toast.LENGTH_LONG).show();
        }else if (count >= 5){
            txtF1_3.setText(""+ results.get(count - 3).getTempInDb());
            txtF2_3.setText(""+ results.get(count - 3).getDataInDb());
            txtF3_3.setText(""+ results.get(count - 3).getDirectionInDb());
            txtF4_3.setText(""+ results.get(count - 3).getWindSpeedInDb());
            txtF5_3.setText(""+ results.get(count - 3).getHumidityInDb());
        }
        realm.commitTransaction();
    }
}
