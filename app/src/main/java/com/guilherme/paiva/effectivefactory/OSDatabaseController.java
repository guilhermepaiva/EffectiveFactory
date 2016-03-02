package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilhermepaiva on 26/02/16.
 */
public class OSDatabaseController {

    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public OSDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertOS(String loteOS, String productOS, String amountOS){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();

        values = new ContentValues();
        values.put(DatabaseHelper.LOTE_OS, loteOS);
        values.put(DatabaseHelper.PRODUCT_OS, productOS);
        values.put(DatabaseHelper.AMOUNT_PRODUCT, amountOS);

        result = db.insert(DatabaseHelper.TABLE_OS, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir ordem de serviço!";
        } else {
            return "Ordem de serviço inserida com sucesso!";
        }
    }

    public Cursor loadOS(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_OS, databaseHelper.LOTE_OS
                , databaseHelper.PRODUCT_OS,
                databaseHelper.AMOUNT_PRODUCT
                , databaseHelper.DATE_CREATED_OS};

        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_OS, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
