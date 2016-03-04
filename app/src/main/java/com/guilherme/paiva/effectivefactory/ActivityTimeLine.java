package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class ActivityTimeLine extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timeline);

        final Spinner spinnerTLEmployee = (Spinner) findViewById(R.id.spinnerTLEmployee);
        final Spinner spinnerTLProduct = (Spinner) findViewById(R.id.spinnerTLProduct);
        ListView listViewTLEmployeeProduct = (ListView) findViewById(R.id.listViewTLEmployeeProduct);
        Button buttonTLFilter = (Button) findViewById(R.id.buttonFilterTimeLine);

        ArrayList<String> arrayListEmployees = new ArrayList<String>();
        arrayListEmployees = arrayListOfEmployeesFromDB();

        ArrayList<String> arrayListOperations = new ArrayList<String>();
        arrayListOperations = arrayListOfOperationssFromDB();


        ArrayAdapter<String> arrayAdapterTLEmployee = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListEmployees);
        ArrayAdapter<String> arrayAdapterTLProducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListOperations);

        spinnerTLEmployee.setAdapter(arrayAdapterTLEmployee);
        spinnerTLProduct.setAdapter(arrayAdapterTLProducts);

        final ArrayList<String> arrayListTLEmployeeOperation = new ArrayList<String>();


        final ArrayAdapter<String> arrayAdapterFilter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayListTLEmployeeOperation);
        listViewTLEmployeeProduct.setAdapter(arrayAdapterFilter);

        buttonTLFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerTLEmployeeString = spinnerTLEmployee.getSelectedItem().toString();
                String spinnerTLProductString = spinnerTLProduct.getSelectedItem().toString();
                arrayListTLEmployeeOperation.add("13:02 " + spinnerTLEmployeeString + " " + spinnerTLProductString);
                //arrayListTLEmployeeOperation.add("13:15 - Abanhar - 25 - 0.20 - R$ 5.00");
                arrayAdapterFilter.notifyDataSetChanged();
            }
        });

    }

    private ArrayList<String> arrayListOfEmployeesFromDB(){
        EmployeeDatabaseController crud = new EmployeeDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadEmployees();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum funcionario cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();
        ArrayList<String> employees = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            employees.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_NAME)));
            cursor.moveToNext();
        }
        cursor.close();

        return employees;

    }

    private ArrayList<String> arrayListOfOperationssFromDB(){
        OperationDatabaseController crud = new OperationDatabaseController(getApplicationContext());
        final Cursor cursor = crud.loadOperations();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhuma operacao cadastrado ainda...", Toast.LENGTH_LONG).show();
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
}
