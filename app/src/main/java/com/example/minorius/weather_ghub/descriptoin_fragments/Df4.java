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
public class Df4 extends Fragment {

    TextView txtF1_4;
    TextView txtF2_4;
    TextView txtF3_4;
    TextView txtF4_4;
    TextView txtF5_4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d4, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        txtF1_4 = (TextView) getView().findViewById(R.id.txtF1_4);
        txtF2_4 = (TextView) getView().findViewById(R.id.txtF2_4);
        txtF3_4 = (TextView) getView().findViewById(R.id.txtF3_4);
        txtF4_4 = (TextView) getView().findViewById(R.id.txtF4_4);
        txtF5_4 = (TextView) getView().findViewById(R.id.txtF5_4);

        Realm realm =  Realm.getInstance(getActivity());
        RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

        realm.beginTransaction();
        int count = results.size();
        if(count < 5){
            Toast.makeText(getActivity(), "You need ON internet", Toast.LENGTH_LONG).show();
        }else if (count >= 5){
            txtF1_4.setText(""+ results.get(count - 2).getTempInDb());
            txtF2_4.setText(""+ results.get(count - 2).getDataInDb());
            txtF3_4.setText(""+ results.get(count - 2).getDirectionInDb());
            txtF4_4.setText(""+ results.get(count - 2).getWindSpeedInDb());
            txtF5_4.setText(""+ results.get(count - 2).getHumidityInDb());
        }
        realm.commitTransaction();
    }
}
