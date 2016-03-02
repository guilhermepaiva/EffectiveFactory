package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilherm on 02/03/2016.
 */
public class SubproductDatabaseController {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public SubproductDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertSubproduct(String productName, String subProductName){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DatabaseHelper.PRODUCT_SUBPRODUCT, productName);
        values.put(DatabaseHelper.SUBPRODUCT_NAME, subProductName);

        result = db.insert(DatabaseHelper.TABLE_SUBPRODUCT, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir o subproduto!";
        } else {
            return "Subproduto inserido com sucesso!";
        }
    }

    public Cursor loadSubproducts(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_SUBPRODUCT, databaseHelper.PRODUCT_SUBPRODUCT, databaseHelper.SUBPRODUCT_NAME};
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_SUBPRODUCT, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }

}
