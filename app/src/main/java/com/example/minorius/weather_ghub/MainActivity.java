package com.example.minorius.weather_ghub;
//START**********
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.WeatherDb.WeatherDbase;
import com.example.minorius.weather_ghub.adapter.UpData;
import com.example.minorius.weather_ghub.adapter.WorkingAdapter;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df1;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df2;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df3;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df4;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df5;
import com.example.minorius.weather_ghub.service.MyService;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private WorkingAdapter adapter;
    public ArrayList<UpData> fetch = new ArrayList<UpData>();
    private ListView lv;

   public GetData gd;

    Df1 df1;
    Df2 df2;
    Df3 df3;
    Df4 df4;
    Df5 df5;

    FragmentManager fm;

    public TextView textView4;
            TextView txtWeather;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gd = new GetData();
        gd.execute();

        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);


        if(isNetworkConnected() == false){

            Realm realm =  Realm.getInstance(getApplicationContext());
            realm.beginTransaction();

            //WeatherDbase weatherDbase = realm.createObject(WeatherDbase.class);
            RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();
            realm.commitTransaction();

            int count = results.size();

            if(count < 5){
                Toast.makeText(getApplicationContext(), "You need ON internet", Toast.LENGTH_LONG).show();
            }
            else if (count >= 5){
                Toast.makeText(getApplicationContext(), "No connecting, data from Db", Toast.LENGTH_LONG).show();
                for(int i = 5; i > 0; i--){
                    UpData a = new UpData(""+results.get(count-i).getDataInDb(),""+results.get(count-i).getTempInDb(), results.get(count-i).getDirectionInDb());
                    fetch.add(a);
                }
            }
        }

        df1 = new Df1();
        df2 = new Df2();
        df3 = new Df3();
        df4 = new Df4();
        df5 = new Df5();

        fm = getFragmentManager();

        final ListView mainList = (ListView) findViewById(R.id.mainList);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction fm = getFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        fm.replace(R.id.buffer, df1).addToBackStack(null).commit();
                        break;
                    case 1:
                        fm.replace(R.id.buffer, df2).addToBackStack(null).commit();
                        break;
                    case 2:
                        fm.replace(R.id.buffer, df3).addToBackStack(null).commit();
                        break;
                    case 3:
                        fm.replace(R.id.buffer, df4).addToBackStack(null).commit();
                        break;
                    case 4:
                        fm.replace(R.id.buffer, df5).addToBackStack(null).commit();
                        break;
                }
            }
        });

        int screenOrientation = getResources().getConfiguration().orientation;

        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
            mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //**************************************
                    switch (position){
                        case 0:
                            Intent i = new Intent(getApplication(), SecondActivity.class);
                            i.putExtra("key", position);
                            startActivity(i);
                            break;
                        case 1:
                            Intent j = new Intent(getApplication(), SecondActivity.class);
                            j.putExtra("key", position);
                            startActivity(j);
                            break;
                        case 2:
                            Intent k = new Intent(getApplication(), SecondActivity.class);
                            k.putExtra("key", position);
                            startActivity(k);
                            break;
                        case 3:
                            Intent l = new Intent(getApplication(), SecondActivity.class);
                            l.putExtra("key", position);
                            startActivity(l);
                            break;
                        case 4:
                            Intent m = new Intent(getApplication(), SecondActivity.class);
                            m.putExtra("key", position);
                            startActivity(m);
                            break;
                    }
//                    FragmentTransaction transaction = fm.beginTransaction();
//                    switch (position) {
//                        case 0:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, df1).addToBackStack(null).commit();
//                            break;
//                        case 1:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, df2).addToBackStack(null).commit();;
//                            break;
//                        case 2:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, df3).addToBackStack(null).commit();;
//                            break;
//                        case 3:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, df4).addToBackStack(null).commit();;
//                            break;
//                        case 4:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, df5).addToBackStack(null).commit();;
//                            break;
//                    }
                }
            });
        }
    }

    private void hideSidePanel() {
        View dataconteiner = findViewById(R.id.dataconteiner);
        if (dataconteiner.getVisibility() == View.VISIBLE) {
            dataconteiner.setVisibility(View.GONE);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    class GetData extends AsyncTask<Void, Void, String> {

       HttpURLConnection urlConnection = null;
       BufferedReader reader = null;
       String result = "";

       @Override
       protected String doInBackground(Void... params) {
           JSONObject dataJsonObj = null;
           String url_for_img = "http://openweathermap.org/img/w/";

           try {

               URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=710791&appid=2de143494c0b295cca9337e1e96b00e0");

               urlConnection = (HttpURLConnection) url.openConnection();
               urlConnection.setRequestMethod("GET");
               urlConnection.connect();

               InputStream inputStream = urlConnection.getInputStream();
               StringBuffer buffer = new StringBuffer();

               reader = new BufferedReader(new InputStreamReader(inputStream));

               String line;
               while ((line = reader.readLine()) != null) {
                   buffer.append(line);
               }
               result = buffer.toString();

           } catch (Exception e) {
              // e.printStackTrace();
           }

           return result;
       }

       @Override
       protected void onPostExecute(String result) {
           super.onPostExecute(result);

           JSONObject dataJsonObj = null;
           String url_for_img = "http://openweathermap.org/img/w/";

           lv = (ListView) findViewById(R.id.mainList);
           adapter = new WorkingAdapter(MainActivity.this, R.id.mainList, fetch);
           lv.setAdapter(adapter);

           try {
               dataJsonObj = new JSONObject((String) result);
               JSONArray list = dataJsonObj.getJSONArray("list");
               for (int i = 0; i < 5; i++) {
                   JSONObject element = list.getJSONObject(i);
                   JSONObject main = element.getJSONObject("main");
                   JSONObject wind = element.getJSONObject("wind");

                   JSONArray weather = element.getJSONArray("weather");
                   String p = weather.getJSONObject(0).getString("main");
                   String icon = weather.getJSONObject(0).getString("icon");

                   String date = element.getString("dt_txt");


                   double temp_min = main.getDouble("temp_min");
                   double temp_min_in_c = temp_min - 273.15;
                   double temp_max = main.getDouble("temp_max");
                   double temp_max_in_c = temp_max - 273.15;
                   int temp = main.getInt("temp");
                   int temp_in_c = temp - 273;

                   int humidity = main.getInt("humidity");
                   double speed = wind.getDouble("speed");

                  // String url_for_img1 = url_for_img + icon + ".png";
                  // Picasso.with(getApplicationContext()).load(url_for_img1);

                   Realm realm =  Realm.getInstance(getApplicationContext());
                   realm.beginTransaction();

                   WeatherDbase weatherDbase = realm.createObject(WeatherDbase.class);

                   weatherDbase.setDataInDb(date + "");
                   weatherDbase.setTempInDb(temp_in_c + "");
                   weatherDbase.setDirectionInDb(p);
                   weatherDbase.setWindSpeedInDb(speed + "");
                   weatherDbase.setHumidityInDb(humidity +"");

                   RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

                   //results.clear();

                   int count = results.size();

                   if(count <= 5 && count > 0){
                       UpData b = new UpData(""+results.get(i).getDataInDb(),""+results.get(i).getTempInDb(), results.get(i).getDirectionInDb());
                       fetch.add(b);
                   }else if(count > 5){
                       UpData b = new UpData(""+results.get(count-6).getDataInDb(),""+results.get(count-6).getTempInDb(), results.get(count-6).getDirectionInDb());
                       fetch.add(b);
                   }

                   realm.commitTransaction();

               }
           } catch (Exception e) {
            //   Toast.makeText(getApplicationContext(), "error " + e, Toast.LENGTH_LONG).show();
           }

       }
   }

}


