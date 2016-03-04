package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class ActivityIndicators extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_indicators);

        ImageButton imageButtonIndicatorOperator = (ImageButton) findViewById(R.id.imageButtonIndicatorOperator);
        ImageButton imageButtonIndicatorProduction = (ImageButton) findViewById(R.id.imageButtonIndicatorProduction);


        imageButtonIndicatorOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityIndicatorOperator.class);
                startActivity(intent);
            }
        });

        imageButtonIndicatorProduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityIndicatorProduction.class);
                startActivity(intent);
            }
        });

    }
}
