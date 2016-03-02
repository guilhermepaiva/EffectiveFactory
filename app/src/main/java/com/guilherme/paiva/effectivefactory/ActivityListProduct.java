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
public class ActivityListProduct extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_product);

        ProductDatabaseController crud = new ProductDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadProducts();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum produto cadastrado ainda...", Toast.LENGTH_LONG).show();
        }
        String[] fieldNames = new String[] {DatabaseHelper.PRODUCT_NAME};
        int[] idViews = new int[] {R.id.idProductName};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_product, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewProducts);
        listView.setAdapter(simpleCursorAdapter);

        Button buttonInsertSubproduct = (Button) findViewById(R.id.buttonRegisterNewProduct);
        buttonInsertSubproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertProduct.class);
                startActivity(intent);
            }
        });

    }
}
