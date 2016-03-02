package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class ActivityIndicators extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_indicators);
    }
}
