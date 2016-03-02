package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilhermepaiva on 13/02/16.
 */
public class FactoryDatabaseController {

    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public FactoryDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertFactory(String factoryName, String factoryAddress){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DatabaseHelper.FACTORY_NAME, factoryName);
        values.put(DatabaseHelper.FACTORY_ADDRESS, factoryAddress);

        result = db.insert(DatabaseHelper.TABLE_FACTORY, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir fábrica!";
        } else {
            return "Fábrica inserida com sucesso!";
        }
    }

    public Cursor loadFactories(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_FACTORY, databaseHelper.FACTORY_NAME};
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_FACTORY, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
