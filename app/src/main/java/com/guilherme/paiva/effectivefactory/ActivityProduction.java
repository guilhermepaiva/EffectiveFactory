package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class ActivityProduction extends Activity {

    private Spinner spinnerEmployee;
    private Spinner spinnerOperations;
    private Button buttonAddproduction;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_production);

        spinnerEmployee = (Spinner) findViewById(R.id.spinnerTLEmployee);
        spinnerOperations = (Spinner) findViewById(R.id.spinnerOperations);
        buttonAddproduction = (Button) findViewById(R.id.buttonAddProduction);


        final TextView textViewE1 = (TextView) findViewById(R.id.textViewE1);
        final TextView textViewO1 = (TextView) findViewById(R.id.textViewO1);
        final Chronometer chronometer1 = (Chronometer) findViewById(R.id.chronometer1);

        final TextView textViewE2 = (TextView) findViewById(R.id.textViewE2);
        final TextView textViewO2 = (TextView) findViewById(R.id.textViewO2);
        final Chronometer chronometer2 = (Chronometer) findViewById(R.id.chronometer2);

        final TextView textViewE3 = (TextView) findViewById(R.id.textViewE3);
        final TextView textViewO3 = (TextView) findViewById(R.id.textViewO3);
        final Chronometer chronometer3 = (Chronometer) findViewById(R.id.chronometer3);

        final TextView textViewE4 = (TextView) findViewById(R.id.textViewE4);
        final TextView textViewO4 = (TextView) findViewById(R.id.textViewO4);
        final Chronometer chronometer4 = (Chronometer) findViewById(R.id.chronometer4);

        ArrayList<String> arrayListEmployees = new ArrayList<String>();
        arrayListEmployees = arrayListOfEmployeesFromDB();

        ArrayList<String> arrayListOperations = new ArrayList<String>();
        arrayListOperations = arrayListOfOperationssFromDB();

        ArrayAdapter<String> arrayAdapterEmployee = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListEmployees);
        ArrayAdapter<String> arrayAdapterOperations = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayListOperations);

        spinnerEmployee.setAdapter(arrayAdapterEmployee);
        spinnerOperations.setAdapter(arrayAdapterOperations);

        buttonAddproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1) {
                    textViewE1.setText(spinnerEmployee.getSelectedItem().toString() + " - ");
                    textViewO1.setText(spinnerOperations.getSelectedItem().toString() + " - ");
                    chronometer1.setVisibility(View.VISIBLE);
                    chronometer1.setBase(SystemClock.elapsedRealtime());
                    chronometer1.start();
                    count++;
                } else if (count == 2) {
                    textViewE2.setText(spinnerEmployee.getSelectedItem().toString() + " - ");
                    textViewO2.setText(spinnerOperations.getSelectedItem().toString() + " - ");
                    chronometer2.setVisibility(View.VISIBLE);
                    chronometer2.setBase(SystemClock.elapsedRealtime());
                    chronometer2.start();
                    count++;
                } else if (count == 3) {
                    textViewE3.setText(spinnerEmployee.getSelectedItem().toString() + " - ");
                    textViewO3.setText(spinnerOperations.getSelectedItem().toString() + " - ");
                    chronometer3.setVisibility(View.VISIBLE);
                    chronometer3.setBase(SystemClock.elapsedRealtime());
                    chronometer3.start();
                    count++;
                } else {
                    textViewE4.setText(spinnerEmployee.getSelectedItem().toString() + " - ");
                    textViewO4.setText(spinnerOperations.getSelectedItem().toString() + " - ");
                    chronometer4.setVisibility(View.VISIBLE);
                    chronometer4.setBase(SystemClock.elapsedRealtime());
                    chronometer4.start();
                    count++;
                }
            }
        });

        Button buttonBackFromProduction = (Button) findViewById(R.id.buttonBackFromProduction);
        buttonBackFromProduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardFactory.class);
                startActivity(intent);
            }
        });
    }



    /*
    private Spinner spinnerEmployee;
    private Spinner spinnerOperations;
    private Button buttonAddproduction;
    private ListView listViewEmployeeOperationsClock;
    private ArrayAdapter<HashMap<String, Chronometer>> arrayAdapterEmployeeOperationsClock;
    //public HashMap<String, Chronometer> hashMapItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_layout_production);

        spinnerEmployee = (Spinner) findViewById(R.id.spinnerEmployee);
        spinnerOperations = (Spinner) findViewById(R.id.spinnerOperations);
        listViewEmployeeOperationsClock = (ListView) findViewById(R.id.listViewEmployeeOperationsClock);

        List<String> listEmployeeTest = new ArrayList<String>();
        List<String> listOperationsTest = new ArrayList<String>();

        listEmployeeTest.add("João");
        listEmployeeTest.add("José");
        listEmployeeTest.add("Luiz");
        listEmployeeTest.add("Pedro");
        listEmployeeTest.add("Paulo");

        listOperationsTest.add("Fechar calça R$ 0.25");
        listOperationsTest.add("Fazer bolso R$ 0.10");
        listOperationsTest.add("Colocar botão R$ 0.15");

        ArrayAdapter<String> arrayAdapterEmployee = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listEmployeeTest);
        ArrayAdapter<String> arrayAdapterOperations = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listOperationsTest);

        spinnerEmployee.setAdapter(arrayAdapterEmployee);
        spinnerOperations.setAdapter(arrayAdapterOperations);

        final ArrayList<HashMap<String, Chronometer>> arrayListEmployeeOperation = new ArrayList<HashMap<String, Chronometer>>();


        arrayAdapterEmployeeOperationsClock = new ArrayAdapter<HashMap<String, Chronometer>>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayListEmployeeOperation);
        listViewEmployeeOperationsClock.setAdapter(arrayAdapterEmployeeOperationsClock);


        buttonAddproduction = (Button) findViewById(R.id.buttonAddProduction);
        buttonAddproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Chronometer> hashMapItemList = new HashMap<String, Chronometer>();
                Chronometer chronometer = new Chronometer(ActivityProduction.this);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                String t = chronometer.getText().toString();

                String employeOp = spinnerEmployee.getSelectedItem().toString() +
                        " - " + spinnerOperations.getSelectedItem().toString();
                hashMapItemList.put(employeOp, chronometer);
                arrayListEmployeeOperation.add(hashMapItemList);
                //arrayListEmployeeOperation.put(spinnerEmployee.getSelectedItem().toString() + " - " +
                  //      spinnerOperations.getSelectedItem().toString(), chronometer);


                arrayAdapterEmployeeOperationsClock.notifyDataSetChanged();

            }

        });


    }
    */

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