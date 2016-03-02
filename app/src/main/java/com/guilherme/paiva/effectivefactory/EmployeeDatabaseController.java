package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guilhermepaiva on 13/02/16.
 */
public class EmployeeDatabaseController {

    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;


    public EmployeeDatabaseController(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertEmployee(String employeeName, String employeeSupervisor, String employeeCell, String employeeCelular){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();

        values = new ContentValues();
        values.put(DatabaseHelper.EMPLOYEE_NAME, employeeName);
        values.put(DatabaseHelper.EMPLOYEE_SUPERVISOR, employeeSupervisor);
        values.put(DatabaseHelper.EMPLOYEE_CELL, employeeCell);
        values.put(DatabaseHelper.EMPLOYEE_CELULAR, employeeCelular);

        result = db.insert(DatabaseHelper.TABLE_EMPLOYEE, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir funcionário!";
        } else {
            return "Funcionário inserido com sucesso!";
        }
    }

    public Cursor loadEmployees(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_EMPLOYEE, databaseHelper.EMPLOYEE_NAME
                , databaseHelper.EMPLOYEE_SUPERVISOR,
                databaseHelper.EMPLOYEE_CELL};

        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_EMPLOYEE, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
