package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by guilhermepaiva on 14/02/16.
 */
public class DashboardFactory extends Activity {

    ImageButton imageButtonRegister;
    ImageButton imageButtonLogin;
    ImageButton imageButtonOS;
    ImageButton imageButtonProduction;
    ImageButton imageButtonTimeLine;
    ImageButton imageButtonIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_factory_layout);

        imageButtonRegister = (ImageButton) findViewById(R.id.imageButtonRegister);
        imageButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);

            }
        });

        imageButtonLogin = (ImageButton) findViewById(R.id.imageButtonLogin);
        imageButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityLogin.class);
                startActivity(intent);
            }
        });

        imageButtonOS = (ImageButton) findViewById(R.id.imageButtonOS);
        imageButtonOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityOS.class);
                startActivity(intent);
            }
        });

        imageButtonProduction = (ImageButton) findViewById(R.id.imageButtonProduction);
        imageButtonProduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityProduction.class);
                startActivity(intent);
            }
        });

        imageButtonTimeLine = (ImageButton) findViewById(R.id.imageButtonTimeLine);
        imageButtonTimeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityTimeLine.class);
                startActivity(intent);
            }
        });

        imageButtonIndicators = (ImageButton) findViewById(R.id.imageButtonIndicators);
        imageButtonIndicators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityIndicators.class);
                startActivity(intent);
            }
        });


    }
}
