package com.woda.budgetcalc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRAS = "com.woda.budgetcalc.EXTRAS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Sends information and starts other view when button is pressed
    public void sendMessage(View view) {

        //Makes sure user fills out all fields with an alert dialog
        if(((EditText)findViewById(R.id.pay_wage_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.general_input)).getText().toString().equals("")||
        ((EditText)findViewById(R.id.emergency_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.housing_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.household_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.phone_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.food_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.entertainment_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.health_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.car_insurance_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.car_payments_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.personal_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.gas_input)).getText().toString().equals("")||
                ((EditText)findViewById(R.id.shopping_input)).getText().toString().equals("")){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    MainActivity.this);


            // set title
            alertDialogBuilder.setTitle("Please fill out all fields");

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        //runs if all the fields are filled
        else {
            //For some reason I decided to do this all on one line
            String[] numbers = {
                    ((EditText) findViewById(R.id.pay_wage_input)).getText().toString(),
                    ((EditText) findViewById(R.id.general_input)).getText().toString(),
                    ((EditText) findViewById(R.id.emergency_input)).getText().toString(),
                    ((EditText) findViewById(R.id.housing_input)).getText().toString(),
                    ((EditText) findViewById(R.id.household_input)).getText().toString(),
                    ((EditText) findViewById(R.id.phone_input)).getText().toString(),
                    ((EditText) findViewById(R.id.food_input)).getText().toString(),
                    ((EditText) findViewById(R.id.entertainment_input)).getText().toString(),
                    ((EditText) findViewById(R.id.health_input)).getText().toString(),
                    ((EditText) findViewById(R.id.car_insurance_input)).getText().toString(),
                    ((EditText) findViewById(R.id.car_payments_input)).getText().toString(),
                    ((EditText) findViewById(R.id.personal_input)).getText().toString(),
                    ((EditText) findViewById(R.id.gas_input)).getText().toString(),
                    ((EditText) findViewById(R.id.shopping_input)).getText().toString()
            };

            //create the intent and add all fo the information
            Bundle extras = new Bundle();
            extras.putStringArray(EXTRAS, numbers);
            Intent intent = new Intent(this, CalculateActivity.class);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

}
