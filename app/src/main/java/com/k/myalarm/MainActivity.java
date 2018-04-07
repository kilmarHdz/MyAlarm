package com.k.myalarm;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText Edit_Hours;
    private EditText Editt_Minutes;
    private Button Botton_Prog;

    private int minute_var = 0;
    private int hour_var = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViews();

        Botton_Prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Edit_Hours.getText().toString().matches("")){

                    if(Integer.parseInt(Edit_Hours.getText().toString()) <0 || Integer.parseInt(Edit_Hours.getText().toString())> 23){
                        hour_var = 0;
                    }else{
                        hour_var = Integer.parseInt(Edit_Hours.getText().toString());
                    }
                }

                if(!Editt_Minutes.getText().toString().matches("")){

                    if(Integer.parseInt(Editt_Minutes.getText().toString()) < 0 || Integer.parseInt(Editt_Minutes.getText().toString()) > 60){
                        minute_var = 0;
                    }else{
                        minute_var = Integer.parseInt(Editt_Minutes.getText().toString());
                    }
                }

                setupAlarm(hour_var, minute_var);
            }
        });

    }

    private void getAllViews(){
        Edit_Hours = findViewById(R.id.Edit_Hours);
        Editt_Minutes = findViewById(R.id.Edit_Minutes);
        Botton_Prog = findViewById(R.id.Btn_Prog);
    }

    private void setupAlarm(int hours, int minutes){

        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);

        alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hours);
        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);

        if(alarmIntent.resolveActivity(getPackageManager()) != null){
            startActivity(alarmIntent);
            Edit_Hours.setText("");
            Editt_Minutes.setText("");
        }
    }
}
