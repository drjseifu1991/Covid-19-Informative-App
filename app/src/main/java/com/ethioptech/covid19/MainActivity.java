package com.ethioptech.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.ethioptech.covid19.models.Alarm;

public class MainActivity extends AppCompatActivity {

    private ImageButton question_imageButton;
    private ImageButton introduction_imageButton;
    private ImageButton symptom_imageButton;
    private ImageButton transmission_imageButton;
    private ImageButton washHand_imageButton;
    private ImageButton mask_imageButton;
    private ImageButton patient_imageButton;
    private ImageButton prevention_imageButton;
    private ImageView new_imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question_imageButton = findViewById(R.id.question_imageButton);
        introduction_imageButton = findViewById(R.id.introduction_imageButton);
        symptom_imageButton = findViewById(R.id.symptom_imageButton);
        transmission_imageButton = findViewById(R.id.transmission_imageButton);
        washHand_imageButton = findViewById(R.id.washHand_imageButton);
        mask_imageButton = findViewById(R.id.mask_imagebutton);
        patient_imageButton = findViewById(R.id.patient_imageButton);
        prevention_imageButton = findViewById(R.id.prevention_imageButton);
        new_imageButton = findViewById(R.id.news_imageButton);

            setAlarm();

        question_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });
        introduction_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Introduction.class);
                startActivity(intent);
            }
        });
        symptom_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SymptomActivity.class);
                startActivity(intent);
            }
        });
        transmission_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransmissionActivity.class);
                startActivity(intent);
            }
        });
        washHand_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WashingHand.class);
                startActivity(intent);
            }
        });
        mask_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HowToUseMask.class);
                startActivity(intent);
            }
        });
        patient_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CovidPatient.class);
                startActivity(intent);
            }
        });
        prevention_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreventionActivity.class);
                startActivity(intent);
            }
        });

        new_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, News.class);
                startActivity(intent);
            }
        });
    }

    public void setAlarm() {
        int time = 43200;
        Intent intent = new Intent(MainActivity.this, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time*1000, time*1000 , pi);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.call_item :
                Intent call_intent=new Intent(MainActivity.this,CallActivity.class);
                startActivity(call_intent);
                break;
            case R.id.about_item :
                AlertDialog.Builder myAlertBuilder=new AlertDialog.Builder(MainActivity.this);
                myAlertBuilder.setTitle(R.string.exit_alert_title);
                myAlertBuilder.setMessage(R.string.exit_message);
                myAlertBuilder.setPositiveButton("እሺ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                myAlertBuilder.setNegativeButton("ኮከብ ይስጡ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog=myAlertBuilder.create();
                alertDialog.show();
                break;
        }
        return true;
    }
}

