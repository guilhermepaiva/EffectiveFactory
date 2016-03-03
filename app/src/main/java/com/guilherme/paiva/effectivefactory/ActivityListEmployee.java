package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 19/02/16.
 */
public class ActivityListEmployee extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_employee);

        EmployeeDatabaseController crud = new EmployeeDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadEmployees();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum funcionário cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        String[] fieldNames = new String[] {DatabaseHelper.EMPLOYEE_NAME, DatabaseHelper.EMPLOYEE_SUPERVISOR, DatabaseHelper.EMPLOYEE_CELL};
        int[] idViews = new int[] {R.id.idEmployeName, R.id.idEmployeSupervisor, R.id.idEmployeCell};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_employee, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewEmployee);
        listView.setAdapter(simpleCursorAdapter);

        Button buttonInsertEmployee = (Button) findViewById(R.id.buttonInsertEmployee);
        buttonInsertEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertEmployee.class);
                startActivity(intent);
            }
        });

        Button buttonBackListEmployee = (Button) findViewById(R.id.buttonBackListEmployee);
        buttonBackListEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });

    }
}
