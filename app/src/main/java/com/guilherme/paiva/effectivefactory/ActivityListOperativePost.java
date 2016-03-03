package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by guilherm on 03/03/2016.
 */
public class ActivityListOperativePost extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_operative_post);

        OperativePostDatabaseController crud = new OperativePostDatabaseController(getBaseContext());

        final Cursor cursor = crud.loadOperativePosts();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum posto operativo cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        String[] fieldNames = new String[] {DatabaseHelper.OPERATIVE_POST_NAME, DatabaseHelper.OPERATIVE_POST_NUMBER};
        int[] idViews = new int[] {R.id.idOperativePostName, R.id.idOperativePostNumber};

        Log.d("CURSOR: ", cursor.getColumnName(2));

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_operative_post, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewOperativePosts);
        listView.setAdapter(simpleCursorAdapter);

        Button buttonInsertOperativePost = (Button) findViewById(R.id.buttonRegisterNewOperativePost);
        buttonInsertOperativePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertOperativePost.class);
                startActivity(intent);
            }
        });

        Button buttonBackListOperativePost = (Button) findViewById(R.id.buttonBackListOperativePost);
        buttonBackListOperativePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }
}
