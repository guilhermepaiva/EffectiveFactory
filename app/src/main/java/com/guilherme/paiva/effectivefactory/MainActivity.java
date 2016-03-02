package com.guilherme.paiva.effectivefactory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActvityInsertFactory.class);
                startActivity(intent);

            }
        });

        FactoryDatabaseController crud = new FactoryDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadFactories();
        Log.d("CURSOR getCount --->>> ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() == 0){
            TextView textViewNoFactory = (TextView) findViewById(R.id.textViewNoFactory);
            textViewNoFactory.setVisibility(View.VISIBLE);
        }

        String[] fieldNames = new String[] {DatabaseHelper.ID_FACTORY, DatabaseHelper.FACTORY_NAME};
        int[] idViews = new int[] {R.id.idFactory, R.id.idFactoryName};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.factories_layout, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewFactory);
        listView.setAdapter(simpleCursorAdapter);


        //when a listview item clicked redirect to a dashboard
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String code;
                cursor.moveToPosition(position);
                code = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ID_FACTORY));
                Intent intent = new Intent(MainActivity.this, DashboardFactory.class);
                intent.putExtra("code", code);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
