package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 16/02/16.
 */
public class ActivityInsertOperativePost extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_operative_post);

        Button buttonCreateOperativePost = (Button) findViewById(R.id.buttonRegisterOperativePost);

        buttonCreateOperativePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperativePostDatabaseController crud = new OperativePostDatabaseController(getBaseContext());

                EditText operativePostName = (EditText) findViewById(R.id.editTextOperativePostName);
                EditText operativePostNumber = (EditText) findViewById(R.id.editTextOperativePostNumber);

                String operativePostNameString = operativePostName.getText().toString();
                String operativePostNumberString = operativePostNumber.getText().toString();

                String result = crud.insertOperativePost(operativePostNameString, operativePostNumberString);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }
}
