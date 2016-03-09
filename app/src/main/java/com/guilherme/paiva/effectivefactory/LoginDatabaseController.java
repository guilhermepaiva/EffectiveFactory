package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilherm on 09/03/2016.
 */
public class LoginDatabaseController {

    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public LoginDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertLogin(String employeeName, String postOperative){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();

        values = new ContentValues();
        values.put(DatabaseHelper.EMPLOYEE_LOGIN, employeeName);
        values.put(DatabaseHelper.OPERATIVE_POST_LOGIN, postOperative);

        result = db.insert(DatabaseHelper.TABLE_LOGIN, null, values);
        db.close();

        if (result == -1){
            return "Erro ao logar funcionario ao posto operativo, tente novamente!";
        } else {
            return "Funcionário logado com sucesso no posto operativo!";
        }
    }

    public Cursor loadLoggedIn(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_LOGIN, databaseHelper.EMPLOYEE_LOGIN
                , databaseHelper.OPERATIVE_POST_LOGIN,
                databaseHelper.DATE_LOGGED};

        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_LOGIN, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
