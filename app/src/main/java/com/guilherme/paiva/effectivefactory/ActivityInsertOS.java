package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 26/02/16.
 */
public class ActivityInsertOS extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_os);

        Button buttonInsertOS = (Button) findViewById(R.id.buttonInsertOS);
        buttonInsertOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OSDatabaseController crud = new OSDatabaseController(getBaseContext());

                EditText editTextLoteOS = (EditText) findViewById(R.id.editTextLoteOS);
                EditText editTextProductOS = (EditText) findViewById(R.id.editTextProductOS);
                EditText editTextAmountProductOS = (EditText) findViewById(R.id.editTextAmountProduct);

                String editTextLoteOSString = editTextLoteOS.getText().toString();
                String editTextProductOSString = editTextProductOS.getText().toString();
                String editTextAmountProductOSString = editTextAmountProductOS.getText().toString();

                String result = crud.insertOS(editTextLoteOSString, editTextProductOSString, editTextAmountProductOSString);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ActivityOS.class);
                startActivity(intent);

            }
        });
    }
}
