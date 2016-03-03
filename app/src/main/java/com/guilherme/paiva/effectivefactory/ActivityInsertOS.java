package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by guilhermepaiva on 26/02/16.
 */
public class ActivityInsertOS extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_os);

        Button buttonInsertOS = (Button) findViewById(R.id.buttonInsertOS);
        Spinner spinnerProductOS = (Spinner) findViewById(R.id.spinnerProductOS);

        ArrayList<String> arrayListProducts = new ArrayList<String>();
        arrayListProducts = arrayListOfProductsFromDB();

        ArrayAdapter<String> arrayAdapterProducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListProducts);
        spinnerProductOS.setAdapter(arrayAdapterProducts);


        buttonInsertOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OSDatabaseController crud = new OSDatabaseController(getBaseContext());

                EditText editTextLoteOS = (EditText) findViewById(R.id.editTextLoteOS);
                Spinner spinnerProductOS = (Spinner) findViewById(R.id.spinnerProductOS);
                //EditText editTextProductOS = (EditText) findViewById(R.id.editTextProductOS);
                EditText editTextAmountProductOS = (EditText) findViewById(R.id.editTextAmountProduct);

                String editTextLoteOSString = editTextLoteOS.getText().toString();
                String spinnerProductOSString = spinnerProductOS.getSelectedItem().toString();
                String editTextAmountProductOSString = editTextAmountProductOS.getText().toString();

                String result = crud.insertOS(editTextLoteOSString, spinnerProductOSString, editTextAmountProductOSString);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ActivityOS.class);
                startActivity(intent);

            }
        });
    }

    private ArrayList<String> arrayListOfProductsFromDB(){
        ProductDatabaseController crud = new ProductDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadProducts();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum produto cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();
        ArrayList<String> products = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            products.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUCT_NAME)));
            cursor.moveToNext();
        }
        cursor.close();

        return products;

    }
}
