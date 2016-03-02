package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class ActivityOS extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_os);

        Button buttonRegisterOS = (Button) findViewById(R.id.buttonRegisterNewOS);

        OSDatabaseController crud = new OSDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadOS();
        Log.d("CURSOR getCount --->>> ", String.valueOf(cursor.getCount()));
        if(cursor.getCount() == 0){
            Log.d("NO RECORD OS", "nenhuma os cadastrada");
        }


        String[] fieldNames = new String[] {DatabaseHelper.ID_OS, DatabaseHelper.LOTE_OS, DatabaseHelper.PRODUCT_OS,
        DatabaseHelper.AMOUNT_PRODUCT, DatabaseHelper.DATE_CREATED_OS};
        int[] idViews = new int[] {R.id.idOS, R.id.idLote, R.id.idProductOS, R.id.idAmount, R.id.idCreatedOS};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.os_layout, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewOS);
        listView.setAdapter(simpleCursorAdapter);


        //when a listview item clicked redirect to a dashboard
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemOSValue;
                cursor.moveToPosition(position);
                itemOSValue = "Identificador da OS: " + cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ID_OS)) + "\n";
                itemOSValue = itemOSValue + " " + cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.LOTE_OS)) + "\n";
                itemOSValue = itemOSValue + " " + cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUCT_OS)) + "\n";
                itemOSValue = itemOSValue + " " + cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.AMOUNT_PRODUCT)) + "\n";
                itemOSValue = itemOSValue + " " + cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DATE_CREATED_OS)) + "\n";
                Intent intent = new Intent(ActivityOS.this, DetailOS.class);
                intent.putExtra("itemOSValue", itemOSValue);
                startActivity(intent);
                finish();
            }
        });

        buttonRegisterOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertOS.class);
                startActivity(intent);
            }
        });
    }
}
