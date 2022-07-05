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
public class CovidPatient extends AppCompatActivity {
    private TextView cleanTouchTitle;
    private TextView cleanTouchDescription;
    private TextView controlSignTitle;
    private TextView controlSignDescription;
    private TextView coverMaskTitle;
    private TextView coverMaskDescription;
    private TextView coverMouseTitle;
    private TextView coverMouseDescription;
    private TextView distanceTitle;
    private TextView distanceDescription;
    private TextView shareTitle;
    private TextView shareDescription;
    private TextView stayTitle;
    private TextView stayDescription;
    private TextView washTitle;
    private TextView washDescription;
    FirebaseFirestore firebaseFirestore;
    private TextView retry;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_patient);
        initializeView();
        firebaseFirestore = FirebaseFirestore.getInstance();
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
        cleanTouchTitle = findViewById(R.id.cleantouchTitle_textView);
        cleanTouchDescription = findViewById(R.id.cleantouchDescription_textView);
        controlSignTitle = findViewById(R.id.controlSignTitle_textView);
        controlSignDescription = findViewById(R.id.controlSignDescription_textView);
        coverMaskTitle = findViewById(R.id.coverMaskTitle_textView);
        coverMaskDescription = findViewById(R.id.coverMaskDescription_textView);
        coverMouseTitle = findViewById(R.id.coverMouthTitle_textView);
        coverMouseDescription = findViewById(R.id.coverMouthDescription_texView);
        distanceTitle = findViewById(R.id.distanceTitle_textView);
        distanceDescription = findViewById(R.id.distanceDescription_textView);
        shareTitle = findViewById(R.id.shareTitle_textView);
        shareDescription = findViewById(R.id.shareDescription_textView);
        stayTitle = findViewById(R.id.stayTitle_textView);
        stayDescription = findViewById(R.id.stayDescription_textView);
        washTitle = findViewById(R.id.washTitle_textView);
        washDescription = findViewById(R.id.washDescription_textView);
        retry = findViewById(R.id.retry);
        progressBar = findViewById(R.id.progressBar2);
    }

    public void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> patientDocument = firebaseFirestore.collection("covidpatient").document("patient").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                cleanTouchTitle.setText(documentSnapshot.getString("cleanthingsyoutouchfrequentlytitle"));
                cleanTouchDescription.setText(documentSnapshot.getString("cleanthingsyoutouchfrequentlydescription").replaceAll("nl", "\n"));
                controlSignTitle.setText(documentSnapshot.getString("controlthesigntitle"));
                controlSignDescription.setText(documentSnapshot.getString("conrolsigndescription").replaceAll("nl", "\n"));
                coverMaskTitle.setText(documentSnapshot.getString("covermouthwithmasktitle"));
                coverMaskDescription.setText(documentSnapshot.getString("covermouthwithmaskdescription").replaceAll("nl", "\n"));
                coverMouseTitle.setText(documentSnapshot.getString("coveryourmouthcoughtitle"));
                coverMouseDescription.setText(documentSnapshot.getString("covermouthcoughdescription").replaceAll("nl", "\n"));
                distanceTitle.setText(documentSnapshot.getString("distancefromhumantitle"));
                distanceDescription.setText(documentSnapshot.getString("distancefromhumandescription").replaceAll("nl", "\n"));
                shareTitle.setText(documentSnapshot.getString("donotsharetitle"));
                shareDescription.setText(documentSnapshot.getString("donotsharedescription").replaceAll("nl", "\n"));
                stayTitle.setText(documentSnapshot.getString("stayhometitle"));
                stayDescription.setText(documentSnapshot.getString("stayhomedescription").replaceAll("nl", "\n"));
                washTitle.setText(documentSnapshot.getString("washhand"));
                washDescription.setText(documentSnapshot.getString("washhanddetail").replaceAll("nl", "\n"));
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