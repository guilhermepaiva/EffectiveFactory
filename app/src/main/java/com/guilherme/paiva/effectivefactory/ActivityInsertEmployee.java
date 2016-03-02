package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 15/02/16.
 */
public class ActivityInsertEmployee extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_employee);

        Button buttonRegisterEmployee = (Button) findViewById(R.id.buttonRegisterEmployee);

        buttonRegisterEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmployeeDatabaseController crud = new EmployeeDatabaseController(getBaseContext());

                EditText editTextEmployeeName = (EditText) findViewById(R.id.editTextEmployeeName);
                EditText editTextSupervisorName = (EditText) findViewById(R.id.editTextSupervisorName);
                EditText editTextCell = (EditText) findViewById(R.id.editTextCell);
                EditText editTextCelular = (EditText) findViewById(R.id.editTextCelular);

                String employeeNameString = editTextEmployeeName.getText().toString();
                String employeeSupervisorString = editTextSupervisorName.getText().toString();
                String employeeCellString = editTextCell.getText().toString();
                String employeeCelularString = editTextCelular.getText().toString();

                String result = crud.insertEmployee(employeeNameString, employeeSupervisorString,
                        employeeCellString, employeeCelularString);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });

    }
}
