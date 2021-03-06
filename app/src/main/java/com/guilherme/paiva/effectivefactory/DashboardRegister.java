package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by guilhermepaiva on 15/02/16.
 */
public class DashboardRegister extends Activity {

    ImageButton imageButtonRegisterEmployee;
    ImageButton imageButtonRegisterOperativePost;
    ImageButton imageButtonRegisterProduct;
    ImageButton imageButtonRegisterPatternTime;
    ImageButton imageButtonRegisterSubproduct;
    ImageButton imageButtonRegisterOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_register);


        imageButtonRegisterEmployee = (ImageButton) findViewById(R.id.imageButtonRegisterEmployee);
        imageButtonRegisterEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), ActivityInsertEmployee.class);
                Intent intent = new Intent(v.getContext(), ActivityListEmployee.class);
                startActivity(intent);
            }
        });

        imageButtonRegisterOperativePost = (ImageButton) findViewById(R.id.imageButtonRegisterOperativePost);
        imageButtonRegisterOperativePost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityListOperativePost.class);
                startActivity(intent);
            }
        });

        imageButtonRegisterProduct = (ImageButton) findViewById(R.id.imageButtonRegisterProduct);
        imageButtonRegisterProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityListProduct.class);
                startActivity(intent);

            }
        });

        imageButtonRegisterPatternTime = (ImageButton) findViewById(R.id.imageButtonRegisterPatternTime);
        imageButtonRegisterPatternTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityListPatternTime.class);
                startActivity(intent);
            }
        });

        imageButtonRegisterSubproduct = (ImageButton) findViewById(R.id.imageButtonRegisterSubproduct);
        imageButtonRegisterSubproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityListSubproduct.class);
                startActivity(intent);
            }
        });

        imageButtonRegisterOperation = (ImageButton) findViewById(R.id.imageButtonRegisterOperation);
        imageButtonRegisterOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityListOperation.class);
                startActivity(intent);

            }
        });

        Button buttonBackDashboardRegister = (Button) findViewById(R.id.buttonBackDashboardRegister);
        buttonBackDashboardRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardFactory.class);
                startActivity(intent);
            }
        });
    }
}
