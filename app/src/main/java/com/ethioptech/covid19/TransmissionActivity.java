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

public class TransmissionActivity extends AppCompatActivity {
    private TextView transmissionTitle_textView;
    private TextView throughCough_textView;
    private TextView handShaking_textView;
    private TextView touchWithHand_textView;
    private TextView animalToHuman_textView;
    FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private TextView retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmisson);
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
        transmissionTitle_textView=findViewById(R.id.transmissiontitle_textview);
        throughCough_textView=findViewById(R.id.throughcough_textview);
        handShaking_textView=findViewById(R.id.handshaking_textview);
        touchWithHand_textView=findViewById(R.id.touchwithhand_textview);
        animalToHuman_textView=findViewById(R.id.animaltohuman__textview);
        progressBar=findViewById(R.id.progressBar2);
        retry=findViewById(R.id.retry);
    }
    public void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> preventionDocument=firebaseFirestore.collection("transmissionmethod").document("transmition").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                transmissionTitle_textView.setText(documentSnapshot.getString("title"));
                throughCough_textView.setText("1. " + documentSnapshot.getString("transmitcough").replaceAll("nl", "\n"));
                handShaking_textView.setText("3. " + documentSnapshot.getString("shakinghand").replaceAll("nl", "\n"));
                touchWithHand_textView.setText("2. " + documentSnapshot.getString("touchwithhand").replaceAll("nl", "\n"));
                animalToHuman_textView.setText("4. " + documentSnapshot.getString("transmitanimaltohuman").replaceAll("nl", "\n"));
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