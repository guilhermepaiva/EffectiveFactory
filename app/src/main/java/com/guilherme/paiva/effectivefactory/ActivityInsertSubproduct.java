package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by guilherm on 02/03/2016.
 */
public class ActivityInsertSubproduct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_subproduct);

        final Spinner spinnerChoiceProduct = (Spinner) findViewById(R.id.spinnerChoiceProduct);
        Button buttonRegisterSubproduct = (Button) findViewById(R.id.buttonRegisterSubproduct);

        ArrayList<String> arrayListProducts = new ArrayList<String>();
        arrayListProducts = arrayListOfProductsFromDB();

        ArrayAdapter<String> arrayAdapterProducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListProducts);
        spinnerChoiceProduct.setAdapter(arrayAdapterProducts);

        buttonRegisterSubproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubproductDatabaseController crud = new SubproductDatabaseController(getBaseContext());

                String spinnerChoiceProductString = spinnerChoiceProduct.getSelectedItem().toString();
                EditText editTextSubproduct = (EditText) findViewById(R.id.editTextSubproduct);
                String editTextSubproductString = editTextSubproduct.getText().toString();

                String result = crud.insertSubproduct(spinnerChoiceProductString, editTextSubproductString);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
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
