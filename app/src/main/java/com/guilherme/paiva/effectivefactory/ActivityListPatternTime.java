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
 * Created by guilherm on 03/03/2016.
 */
public class ActivityListPatternTime extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_pattern_time);

        PatternTimeDatabaseController crud = new PatternTimeDatabaseController(getBaseContext());

        final Cursor cursor = crud.loadPatternTimes();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhum tempo padrão cadastrado ainda...", Toast.LENGTH_LONG).show();
        }

        String[] fieldNames = new String[] {DatabaseHelper.OPERATION_PATTERN_TIME, DatabaseHelper.TIME_PATTERN_TIME};
        int[] idViews = new int[] {R.id.idOperationPatternTime, R.id.idTimePatternTime};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_pattern_time, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewPatternTimes);
        listView.setAdapter(simpleCursorAdapter);

        Button buttonInsertPatternTime = (Button) findViewById(R.id.buttonRegisterNewPatternTime);
        buttonInsertPatternTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityInsertPatternTime.class);
                startActivity(intent);
            }
        });

        Button buttonBackListPatternTime = (Button) findViewById(R.id.buttonBackListPatternTime);
        buttonBackListPatternTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardRegister.class);
                startActivity(intent);
            }
        });
    }
}
