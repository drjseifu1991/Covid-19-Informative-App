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

public class HowToUseMask extends AppCompatActivity {
    private TextView howToUseMaskTitle;
    private TextView howToUseMaskDescription;
    private TextView whenToUseMaskTitle;
    private TextView whenToUseMaskDescription;
    FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private TextView retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use_mask);
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
        howToUseMaskTitle=findViewById(R.id.howToUseMaskTitle_textView);
        howToUseMaskDescription=findViewById(R.id.howToUseMaskdescription_textView);
        whenToUseMaskTitle=findViewById(R.id.whenToUseMaskTitle_textView);
        whenToUseMaskDescription=findViewById(R.id.whenToUseMaskDescription_textView);
        progressBar=findViewById(R.id.progressBar2);
        retry=findViewById(R.id.retry);
    }
    public void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> howToUseMaskDocument=firebaseFirestore.collection("howtousemask").document("maskuse").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                howToUseMaskTitle.setText(documentSnapshot.getString("maskusetitle"));
                howToUseMaskDescription.setText(documentSnapshot.getString("maskusedescription").replaceAll("nl","\n"));
                whenToUseMaskTitle.setText(documentSnapshot.getString("whentousemasktitle"));
                whenToUseMaskDescription.setText(documentSnapshot.getString("whentousemaskdescription").replaceAll("nl","\n"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
            }

        });
    }
}