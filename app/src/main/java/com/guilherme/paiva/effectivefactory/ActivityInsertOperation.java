package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 02/03/16.
 */
public class ActivityInsertOperation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_operation);

        Button buttonRegisterOperation = (Button) findViewById(R.id.buttonRegisterOperation);

        buttonRegisterOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperationDatabaseController crud = new OperationDatabaseController(getBaseContext());

                EditText descriptionOperation = (EditText) findViewById(R.id.editTextDescriptionOperation);

                String descriptionOperationString = descriptionOperation.getText().toString();

                String result = crud.insertOperation(descriptionOperationString);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ActivityListOperation.class);
                startActivity(intent);
            }
        });
    }
}
