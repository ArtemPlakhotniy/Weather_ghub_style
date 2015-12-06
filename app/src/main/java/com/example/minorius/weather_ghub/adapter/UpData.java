package com.example.minorius.weather_ghub.adapter;

import android.media.Image;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.MainActivity;
import com.example.minorius.weather_ghub.WeatherDb.WeatherDbase;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Minorius on 22.11.2015.
 */
public class UpData extends MainActivity {

    String date;
    String temp;
    String p;

    public UpData(String date, String temp, String p) {
        this.date = date;
        this.temp = temp;
        this.p = p;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

}