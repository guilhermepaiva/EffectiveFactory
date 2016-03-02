package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 13/02/16.
 */
public class ActvityInsertFactory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_factory);

        Button buttonCreateFactory = (Button) findViewById(R.id.buttonCreateFactory);

        buttonCreateFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FactoryDatabaseController crud = new FactoryDatabaseController(getBaseContext());

                EditText factoryName = (EditText) findViewById(R.id.editTextFactoryName);
                EditText factoryAddress = (EditText) findViewById(R.id.editTextFactoryAddress);

                String factoryNameString = factoryName.getText().toString();
                String factoryAddressString = factoryAddress.getText().toString();

                String result = crud.insertFactory(factoryNameString, factoryAddressString);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
