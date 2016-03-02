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
public class ActivityInsertProduct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_product);

        Button buttonRegisterProduct = (Button) findViewById(R.id.buttonRegisterProduct);

        buttonRegisterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDatabaseController crud = new ProductDatabaseController(getBaseContext());

                EditText productName = (EditText) findViewById(R.id.editTextProductName);

                String productNameString = productName.getText().toString();

                String result = crud.insertProduct(productNameString);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }
}
