package com.guilherme.paiva.effectivefactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guilhermepaiva on 02/03/16.
 */
public class OperationDatabaseController {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public OperationDatabaseController(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public String insertOperation(String descriptionOperation, String productOperation,
                                  String subproductOperation, String costOperation){
        ContentValues values;
        long result;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DatabaseHelper.DESCRIPTION_OPERATION, descriptionOperation);
        values.put(DatabaseHelper.PRODUCT_OPERATION, productOperation);
        values.put(DatabaseHelper.SUBPRODUCT_OPERATION, subproductOperation);
        values.put(DatabaseHelper.COST_OPERATION, costOperation);

        result = db.insert(DatabaseHelper.TABLE_OPERATION, null, values);
        db.close();

        if (result == -1){
            return "Erro ao inserir a operação!";
        } else {
            return "Operação inserida com sucesso!";
        }
    }

    public Cursor loadOperations(){
        Cursor cursor;
        String[] fields = {databaseHelper.ID_OPERATION, databaseHelper.DESCRIPTION_OPERATION, DatabaseHelper.PRODUCT_OPERATION, DatabaseHelper.SUBPRODUCT_OPERATION, DatabaseHelper.COST_OPERATION};
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(databaseHelper.TABLE_OPERATION, fields, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
}
