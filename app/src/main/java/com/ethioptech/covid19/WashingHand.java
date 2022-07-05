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


public class WashingHand extends AppCompatActivity {
    private TextView title_textView;
    private TextView step1_textView;
    private TextView step2_textView;
    private TextView step3_textView;
    private TextView step4_textView;
    private TextView step5_textView;
    private TextView step6_textView;
    private TextView general_textView;
    FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private TextView retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_hand);
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

    private void initializeView() {
        title_textView=findViewById(R.id.title_textView);
        step1_textView=findViewById(R.id.step1_textView);
        step2_textView=findViewById(R.id.step2_textView);
        step3_textView=findViewById(R.id.step3_textView);
        step4_textView=findViewById(R.id.step4_textView);
        step5_textView=findViewById(R.id.step5_textView);
        step6_textView=findViewById(R.id.step6_textView);
        general_textView=findViewById(R.id.generalWash_textView);
        progressBar=findViewById(R.id.progressBar2);
        retry=findViewById(R.id.retry);
    }
    public  void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> handWashDocument=firebaseFirestore.collection("howtowashhand").document("washinghand").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                title_textView.setText(documentSnapshot.getString("washinghandtitle"));
                step1_textView.setText(documentSnapshot.getString("washinghandstep1").replaceAll("nl","\n"));
                step2_textView.setText(documentSnapshot.getString("washinghandstep2").replaceAll("nl","\n"));
                step3_textView.setText(documentSnapshot.getString("washinghandstep3").replaceAll("nl","\n"));
                step4_textView.setText(documentSnapshot.getString("washinghandstep4").replaceAll("nl","\n"));
                step5_textView.setText(documentSnapshot.getString("washinghandstep5").replaceAll("nl","\n"));
                step6_textView.setText(documentSnapshot.getString("washinghandstep6").replaceAll("nl","\n"));
                general_textView.setText(documentSnapshot.getString("washinghandgeneralization").replaceAll("nl","\n"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"please connect to internet",Toast.LENGTH_SHORT).show();
            }
        });
    }
}