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
 * Created by guilhermepaiva on 02/03/16.
 */
public class ActivityInsertOperation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_operation);

        final Spinner spinnerProductOperation = (Spinner) findViewById(R.id.spinnerProductOperation);

        ArrayList<String> arrayListProducts = new ArrayList<String>();
        arrayListProducts = arrayListOfProductsFromDB();

        ArrayAdapter<String> arrayAdapterProducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListProducts);
        spinnerProductOperation.setAdapter(arrayAdapterProducts);


        final Spinner spinnerSubproductOperation = (Spinner) findViewById(R.id.spinnerSubproductOperation);

        ArrayList<String> arrayListSubproducts = new ArrayList<String>();
        arrayListSubproducts = arrayListOfSubproductsFromDB();

        ArrayAdapter<String> arrayAdapterSubproducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListSubproducts);
        spinnerSubproductOperation.setAdapter(arrayAdapterSubproducts);

        Button buttonRegisterOperation = (Button) findViewById(R.id.buttonRegisterOperation);
        Button buttonBackInsertOperation = (Button) findViewById(R.id.buttonBackInsertOperation);

        buttonRegisterOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperationDatabaseController crud = new OperationDatabaseController(getBaseContext());

                EditText descriptionOperation = (EditText) findViewById(R.id.editTextDescriptionOperation);
                EditText costOperation = (EditText) findViewById(R.id.editTextCostOperation);

                String descriptionOperationString = descriptionOperation.getText().toString();
                String productOperationString = spinnerProductOperation.getSelectedItem().toString();
                String subproductOperationString = spinnerSubproductOperation.getSelectedItem().toString();
                String costOperationString = costOperation.getText().toString();

                String result = crud.insertOperation(descriptionOperationString, productOperationString, subproductOperationString, costOperationString);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ActivityListOperation.class);
                startActivity(intent);
            }
        });

        buttonBackInsertOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private ArrayList<String> arrayListOfSubproductsFromDB(){
        SubproductDatabaseController crud = new SubproductDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadSubproducts();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum subproduto cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();
        ArrayList<String> subproducts = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            subproducts.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SUBPRODUCT_NAME)));
            cursor.moveToNext();
        }
        cursor.close();

        return subproducts;

    }
}
