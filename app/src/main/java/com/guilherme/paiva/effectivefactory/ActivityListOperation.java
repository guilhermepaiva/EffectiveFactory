package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by guilhermepaiva on 02/03/16.
 */
public class ActivityListOperation extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_operation);

        OperationDatabaseController crud = new OperationDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadOperations();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhuma operação cadastrada ainda...", Toast.LENGTH_LONG).show();
        }
        String[] fieldNames = new String[] {DatabaseHelper.DESCRIPTION_OPERATION, DatabaseHelper.PRODUCT_OPERATION,
        DatabaseHelper.SUBPRODUCT_OPERATION, DatabaseHelper.COST_OPERATION};
        int[] idViews = new int[] {R.id.idDescriptionOperation, R.id.idProductOperation, R.id.idSubproductOperation,
        R.id.idCostOperation};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_operation, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewOperations);
        listView.setAdapter(simpleCursorAdapter);

        Button buttonInsertOperation = (Button) findViewById(R.id.buttonRegisterNewOperation);
        buttonInsertOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertOperation.class);
                startActivity(intent);
            }
        });

        Button buttonBackListOperation = (Button) findViewById(R.id.buttonBackListOperation);
        buttonBackListOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }
}
