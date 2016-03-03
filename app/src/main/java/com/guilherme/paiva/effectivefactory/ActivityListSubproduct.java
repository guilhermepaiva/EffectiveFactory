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
 * Created by guilherm on 02/03/2016.
 */
public class ActivityListSubproduct extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_subproduct);

        SubproductDatabaseController crud = new SubproductDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadSubproducts();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum subproduto cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        String[] fieldNames = new String[] {DatabaseHelper.PRODUCT_SUBPRODUCT, DatabaseHelper.SUBPRODUCT_NAME};
        int[] idViews = new int[] {R.id.idProductSobproductName, R.id.idSubproductName};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_subproduct, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewSubproducts);
        listView.setAdapter(simpleCursorAdapter);

        Button buttonInsertSubproduct = (Button) findViewById(R.id.buttonInsertSubproduct);
        buttonInsertSubproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertSubproduct.class);
                startActivity(intent);
            }
        });

        Button buttonBackListSubproduct = (Button) findViewById(R.id.buttonBackListSubproduct);
        buttonBackListSubproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }
}
