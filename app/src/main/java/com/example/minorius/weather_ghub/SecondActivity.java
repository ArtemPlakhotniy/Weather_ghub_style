package com.example.minorius.weather_ghub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.descriptoin_fragments.Df1;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df2;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df3;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df4;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df5;

/**
 * Created by Minorius on 07.12.2015.
 */
public class SecondActivity extends AppCompatActivity {

    FragmentManager fm;
    Df1 df1;
    Df2 df2;
    Df3 df3;
    Df4 df4;
    Df5 df5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar tb = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.drawable.chevron_left);
        tb.setSubtitle("minorius");

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        df1 = new Df1();
        df2 = new Df2();
        df3 = new Df3();
        df4 = new Df4();
        df5 = new Df5();

        fm = getFragmentManager();

        Bundle b = getIntent().getExtras();

        FragmentTransaction transaction = fm.beginTransaction();
        int screenOrientation = getResources().getConfiguration().orientation;

        switch (b.getInt("key")){
            case 0:
                transaction.replace(R.id.buffer4, df1).commit();
                if(screenOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    finish();
                }
                break;
            case 1:
                transaction.replace(R.id.buffer4, df2).commit();
                if(screenOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    finish();
                }
                break;
            case 2:
                transaction.replace(R.id.buffer4, df3).commit();
                if(screenOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    finish();
                }
                break;
            case 3:
                transaction.replace(R.id.buffer4, df4).commit();
                if(screenOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    finish();
                }
                break;
            case 4:
                transaction.replace(R.id.buffer4, df5).commit();
                if(screenOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    finish();
                }
                break;
        }

    }

}
