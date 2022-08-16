package com.example.secret;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  Button button = null;
    private boolean isMan;
    private Toast toast;
    private int month;
    private int day;
    private int hour;
   // private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add the radio button event
        RadioGroup radio_group = (RadioGroup)findViewById(R.id.radio_group);

        //add the spinner event
        Spinner spinner_month=findViewById(R.id.edit_month);
        spinner_month.setSelection(0);

        Spinner spinner_day=findViewById(R.id.edit_day);
        spinner_day.setSelection(0);

        Spinner spinner_hour=findViewById(R.id.edit_hour);
        spinner_hour.setSelection(0);

        spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                month = pos;

                                                    }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                day = pos;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                hour = pos;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //add the button event
        button = (Button)findViewById(R.id.btn_calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Debug:", "button down");
                int i;

                for (i=0; i< radio_group.getChildCount(); i++){
                    RadioButton radioButton=(RadioButton) radio_group.getChildAt(i);
                    if (radioButton.isChecked()){
                        break;
                    }
                }

                if(i==0){
                    isMan = true;
                }

                else if( i == (radio_group.getChildCount() -1))
                {
                    isMan = false;
                }

                else{
                    toast = Toast.makeText(getApplicationContext(),"性别选错了",Toast.LENGTH_LONG);
                }

                if(isMan)
                {
                    Log.d("Debug:", String.format("man ,month: %d , day:%d , hour: %d", month, day, hour));
                   // value = String.format("man,month:%d,day:%d,hour:%d", month, day, hour);
                }
                else
                {
                    Log.d("Debug:", String.format("woman ,month: %d , day:%d , hour: %d", month, day, hour));
                   // value = String.format("woman ,month: %d , day:%d , hour: %d", month, day, hour);
                }


                Intent mIntent =  new Intent(MainActivity.this, DetailShow.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("sex", isMan);
                bundle.putInt("month", month);
                bundle.putInt("day", day);
                bundle.putInt("hour", hour);
                mIntent.putExtras(bundle);
                startActivity(mIntent);
                //finish();
            }
        });

    }



}