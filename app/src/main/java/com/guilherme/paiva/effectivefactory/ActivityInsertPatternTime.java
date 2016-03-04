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
 * Created by guilherm on 03/03/2016.
 */
public class ActivityInsertPatternTime extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_pattern_time);


        //spinner de product
        final Spinner spinnerProductPatternTime = (Spinner) findViewById(R.id.spinnerProductPatternTime);

        ArrayList<String> arrayListProducts = new ArrayList<String>();
        arrayListProducts = arrayListOfProductsFromDB();

        ArrayAdapter<String> arrayAdapterProducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListProducts);
        spinnerProductPatternTime.setAdapter(arrayAdapterProducts);

        //spinner de subproduct
        final Spinner spinnerSubproductPatternTime = (Spinner) findViewById(R.id.spinnerSubproductPatternTime);

        ArrayList<String> arrayListSubproducts = new ArrayList<String>();
        arrayListSubproducts = arrayListOfSubproductsFromDB();

        ArrayAdapter<String> arrayAdapterSubproducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListSubproducts);
        spinnerSubproductPatternTime.setAdapter(arrayAdapterSubproducts);

        //spinner de operation description
        final Spinner spinnerOperationPatternTime = (Spinner) findViewById(R.id.spinnerOperationPatternTime);

        ArrayList<String> arrayListOperations = new ArrayList<String>();
        arrayListOperations = arrayListOfOperationsFromDB();

        ArrayAdapter<String> arrayAdapterOperations = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListOperations);
        spinnerOperationPatternTime.setAdapter(arrayAdapterOperations);

        Button buttonRegisterPatternTime = (Button) findViewById(R.id.buttonRegisterNewPatternTime);
        buttonRegisterPatternTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatternTimeDatabaseController crud = new PatternTimeDatabaseController(getBaseContext());

                EditText editTextTimePatternTime = (EditText) findViewById(R.id.editTextTimePatternTime);

                String editTextTimePatternTimeString = editTextTimePatternTime.getText().toString();
                String spinnerOperationPatternTimeString = spinnerOperationPatternTime.getSelectedItem().toString();

                String result = crud.insertPatternTime(editTextTimePatternTimeString, spinnerOperationPatternTimeString);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ActivityListPatternTime.class);
                startActivity(intent);

            }
        });


        Button buttonBackPatternTime = (Button) findViewById(R.id.buttonBackPatternTime);
        buttonBackPatternTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> arrayListOfOperationsFromDB(){
        OperationDatabaseController crud = new OperationDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadOperations();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhuma operação cadastrada ainda...", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();
        ArrayList<String> operations = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            operations.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_OPERATION)));
            cursor.moveToNext();
        }
        cursor.close();

        return operations;

    }

    private ArrayList<String> arrayListOfProductsFromDB(){
        OperationDatabaseController crud = new OperationDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadOperations();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhuma operação cadastrada ainda...", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();
        ArrayList<String> products = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            products.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUCT_OPERATION)));
            cursor.moveToNext();
        }
        cursor.close();

        return products;

    }

    private ArrayList<String> arrayListOfSubproductsFromDB(){
        OperationDatabaseController crud = new OperationDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadOperations();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhuma operação cadastrada ainda...", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();
        ArrayList<String> subproducts = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            subproducts.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SUBPRODUCT_OPERATION)));
            cursor.moveToNext();
        }
        cursor.close();

        return subproducts;

    }
}
