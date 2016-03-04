package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilherm on 03/03/2016.
 */
public class PatternTimeDatabaseController {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public PatternTimeDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertPatternTime(String operationPatternTime, String timePatternTime){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DatabaseHelper.OPERATION_PATTERN_TIME, operationPatternTime);
        values.put(DatabaseHelper.TIME_PATTERN_TIME, timePatternTime);

        result = db.insert(DatabaseHelper.TABLE_PATTERN_TIME, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir tempo padrão!";
        } else {
            return "Tempo padrão inserido com sucesso!";
        }
    }

    public Cursor loadPatternTimes(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_PATTERN_TIME, databaseHelper.PRODUCT_PATTERN_TIME, databaseHelper.SUBPRODUCT_PATTERN_TIME,
        databaseHelper.OPERATION_PATTERN_TIME, databaseHelper.TIME_PATTERN_TIME};
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_PATTERN_TIME, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
