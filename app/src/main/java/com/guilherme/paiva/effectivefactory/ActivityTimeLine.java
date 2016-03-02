package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class ActivityTimeLine extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timeline);

        Spinner spinnerTLEmployee = (Spinner) findViewById(R.id.spinnerTLEmployee);
        Spinner spinnerTLProduct = (Spinner) findViewById(R.id.spinnerTLProduct);
        ListView listViewTLEmployeeProduct = (ListView) findViewById(R.id.listViewTLEmployeeProduct);
        Button buttonTLFilter = (Button) findViewById(R.id.buttonFilterTimeLine);

        List<String> listEmployeeTest = new ArrayList<String>();
        List<String> listProductsTest = new ArrayList<String>();

        listEmployeeTest.add("João");
        listEmployeeTest.add("José");
        listEmployeeTest.add("Luiz");
        listEmployeeTest.add("Pedro");
        listEmployeeTest.add("Paulo");

        listProductsTest.add("Calça");
        listProductsTest.add("Bolso");
        listProductsTest.add("Camisa");
        listProductsTest.add("Ziper");
        listProductsTest.add("Bermuda");
        listProductsTest.add("Saia");
        listProductsTest.add("Short");
        listProductsTest.add("Camiseta");

        ArrayAdapter<String> arrayAdapterTLEmployee = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listEmployeeTest);
        ArrayAdapter<String> arrayAdapterTLProducts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listProductsTest);

        spinnerTLEmployee.setAdapter(arrayAdapterTLEmployee);
        spinnerTLProduct.setAdapter(arrayAdapterTLProducts);

        final ArrayList<String> arrayListTLEmployeeProduct = new ArrayList<String>();


        final ArrayAdapter<String> arrayAdapterFilter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayListTLEmployeeProduct);
        listViewTLEmployeeProduct.setAdapter(arrayAdapterFilter);

        buttonTLFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListTLEmployeeProduct.add("13:02 - Fechar Calça - 50 - 0.20 - R$ 10.00");
                arrayListTLEmployeeProduct.add("13:15 - Abanhar - 25 - 0.20 - R$ 5.00");
                arrayAdapterFilter.notifyDataSetChanged();
            }
        });

    }
}
