package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilhermepaiva on 16/02/16.
 */
public class ProductDatabaseController {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public ProductDatabaseController(Context context){
            databaseHelper = new DatabaseHelper(context);
    }

    public String insertProduct(String productName){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DatabaseHelper.PRODUCT_NAME, productName);

        result = db.insert(DatabaseHelper.TABLE_PRODUCT, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir o produto!";
        } else {
            return "Produto inserido com sucesso!";
        }
    }

    public Cursor loadProducts(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_PRODUCT, databaseHelper.PRODUCT_NAME};
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_PRODUCT, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
