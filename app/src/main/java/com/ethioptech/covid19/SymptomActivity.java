package com.ethioptech.covid19;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SymptomActivity extends AppCompatActivity {
    private TextView fever_textView;
    private TextView cough_textView;
    private TextView breathing_textView;
    private TextView fatigue_textView;
    private TextView bodyFever_textView;
    private TextView body_textView;
    private TextView mugus_textView;
    private TextView worst_textView;
    private TextView lung_textView;
    private TextView kidney_textView;
    private TextView general_textView;
    FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    TextView retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        initializeView();
        firebaseFirestore=FirebaseFirestore.getInstance();
        fetchData();
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry.setVisibility(View.INVISIBLE);
                fetchData();
            }
        });
    }
    public void initializeView(){
        fever_textView=findViewById(R.id.fever_textview);
        cough_textView=findViewById(R.id.cough_textview);
        breathing_textView=findViewById(R.id.breathing_textview);
        fatigue_textView=findViewById(R.id.fatigue_textview);
        bodyFever_textView=findViewById(R.id.bodyfever_textview);
        body_textView=findViewById(R.id.body_textview);
        mugus_textView=findViewById(R.id.mugus_textview);
        worst_textView=findViewById(R.id.wrost_textview);
        lung_textView=findViewById(R.id.lungdisease_textview);
        kidney_textView=findViewById(R.id.kidenyfailure_textview);
        general_textView=findViewById(R.id.general_textview);
        progressBar=findViewById(R.id.progressBar2);
        retry=findViewById(R.id.retry);
    }
    public void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> symptom_document = firebaseFirestore.collection("symptoms").document("symptom").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                fever_textView.setText(documentSnapshot.getString("fever").replaceAll("nl", "\n"));
                cough_textView.setText(documentSnapshot.getString("cough").replaceAll("nl", "\n"));
                breathing_textView.setText(documentSnapshot.getString("short breath").replaceAll("nl", "\n"));
                fatigue_textView.setText(documentSnapshot.getString("fatigue").replaceAll("nl", "\n"));
                bodyFever_textView.setText(documentSnapshot.getString("bodyfever").replaceAll("nl", "\n"));
                body_textView.setText(documentSnapshot.getString("tiredness").replaceAll("nl", "\n"));
                mugus_textView.setText(documentSnapshot.getString("mocus").replaceAll("nl", "\n"));
                worst_textView.setText(documentSnapshot.getString("worstcondition").replaceAll("nl", "\n"));
                lung_textView.setText(documentSnapshot.getString("pnemonia").replaceAll("nl", "\n"));
                kidney_textView.setText(documentSnapshot.getString("kidneyfailuredeath").replaceAll("nl", "\n"));
                general_textView.setText(documentSnapshot.getString("description").replaceAll("nl", "\n"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "connect to internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}