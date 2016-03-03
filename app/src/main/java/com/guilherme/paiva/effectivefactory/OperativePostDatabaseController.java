package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilhermepaiva on 16/02/16.
 */
public class OperativePostDatabaseController {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public OperativePostDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertOperativePost(String operativePostName, String operativePostNumber){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DatabaseHelper.OPERATIVE_POST_NAME, operativePostName);
        values.put(DatabaseHelper.OPERATIVE_POST_NUMBER, operativePostNumber);

        result = db.insert(DatabaseHelper.TABLE_OPERATIVE_POST, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir Posto Operativo!";
        } else {
            return "Posto Operativo inserido com sucesso!";
        }
    }

    public Cursor loadOperativePosts(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_OPERATIVE_POST, databaseHelper.OPERATIVE_POST_NAME, databaseHelper.OPERATIVE_POST_NUMBER};
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_OPERATIVE_POST, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
