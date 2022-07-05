package com.ethioptech.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class News extends AppCompatActivity {
  private TextView textView;
          TextView retry;
          ProgressBar progressBar;
  FirebaseFirestore firebaseFirestore;
  private TextView ethioInfected;
    private TextView ethioRecoverd;
    private TextView ethioDeath;
    private TextView africaInfected;
    private TextView africaRecovered;
    private TextView africaDeath;
    private TextView worldInfected;
    private TextView worldRecovered;
    private TextView worldDeath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        retry=findViewById(R.id.tvRetry);
        progressBar=findViewById(R.id.newProgressBar);
        ethioInfected=findViewById(R.id.ethio_infected);
        ethioRecoverd=findViewById(R.id.ethio_recovered);
        ethioDeath=findViewById(R.id.ethio_death);
        africaDeath=findViewById(R.id.africa_death);
        africaInfected=findViewById(R.id.africa_infected);
        africaRecovered=findViewById(R.id.africa_recovered);
        worldDeath=findViewById(R.id.world_death);
        worldInfected=findViewById(R.id.world_infected);
        worldRecovered=findViewById(R.id.world_recovered);
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

    public  void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<QuerySnapshot> whatIsCorona_Document=firebaseFirestore.collection("news").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    List<DocumentSnapshot> documents =task.getResult().getDocuments();
                    if(documents.size()>0){
                        africaInfected.setText(documents.get(0).getString("infected"));
                        africaDeath.setText(documents.get(0).getString("death"));
                        africaRecovered.setText(documents.get(0).getString("recovered"));
                        ethioDeath.setText(documents.get(1).getString("death"));
                        ethioInfected.setText(documents.get(1).getString("infected"));
                        ethioRecoverd.setText(documents.get(1).getString("recovered"));
                        worldDeath.setText(documents.get(2).getString("death"));
                        worldInfected.setText(documents.get(2).getString("infected"));
                        worldRecovered.setText(documents.get(2).getString("recovered")); }
                    else{
                        Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
                        retry.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
                    retry.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}