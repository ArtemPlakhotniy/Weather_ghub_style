package com.example.minorius.weather_ghub.service;
//START**********
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.ListView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.MainActivity;
import com.example.minorius.weather_ghub.R;
import com.example.minorius.weather_ghub.WeatherDb.WeatherDbase;
import com.example.minorius.weather_ghub.adapter.UpData;
import com.example.minorius.weather_ghub.adapter.WorkingAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyService extends Service {

    private MainActivity ma;

    private NotificationManager nm;
    private final int NOTIFICATION_ID = 001;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       callAsynchronousTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(){
        nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new  Notification.Builder(getApplicationContext());
        Intent i = new Intent(getApplicationContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("update")
                .addAction(R.mipmap.cached, "Click to update", pendingIntent)
                //.addAction(R.mipmap.cached, "Clear cash", pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Click for open app")
                .setContentText("weather");

        Notification notification = builder.build();
        nm.notify(NOTIFICATION_ID, notification);
    }

   class PingServer extends AsyncTask <Void, Void, String>{

       HttpURLConnection urlConnection = null;
       BufferedReader reader = null;
       String result = "";

       @Override
       protected String doInBackground(Void... params) {

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

                   String url_for_img1 = url_for_img + icon + ".png";

                   Realm realm =  Realm.getInstance(getApplicationContext());
                   realm.beginTransaction();

                   WeatherDbase weatherDbase = realm.createObject(WeatherDbase.class);


                   weatherDbase.setDataInDb(date + "");
                   weatherDbase.setTempInDb(temp_in_c + "");
                   weatherDbase.setDirectionInDb(p);
                   weatherDbase.setWindSpeedInDb(speed + "");
                   weatherDbase.setHumidityInDb(humidity + "");
                   weatherDbase.setIconInDb(url_for_img1 + "");

                   RealmResults<WeatherDbase> results = realm.where(WeatherDbase.class).findAll();

                   int count = results.size();
                   if(count > 200){
                       for(int l = 1; l <= 100; l++){
                           WeatherDbase weatherDbase1 = results.get(i);
                           weatherDbase1.removeFromRealm();

                       }
                   }

                   //Toast.makeText(getApplicationContext(), ""+ count, Toast.LENGTH_SHORT).show();

                   realm.commitTransaction();


               }
           } catch (Exception e) {
               //Toast.makeText(getApplicationContext(), "error " + e, Toast.LENGTH_LONG).show();
           }
       }
   }

    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            PingServer PingServer = new PingServer();
                            PingServer.execute();
                            showNotification();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 180000);
    }

}
