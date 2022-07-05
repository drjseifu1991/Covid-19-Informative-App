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

public class Introduction extends AppCompatActivity {
    private TextView whatIsCorona_title;
    private TextView whatIsCorona_description;
    private TextView originOfCorona_title;
    private TextView originOfCorona_description;
    private TextView coronaHasMedicine_title;
    private TextView coronaHasMedicine_description;
    private TextView canWeStopCorona_title;
    private TextView canWeStopCorona_description;
    private TextView whenCoronaSpread_title;
    private TextView whenCoronaSpread_description;
    private TextView retry;
    private ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
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
        whatIsCorona_title=findViewById(R.id.whatiscorona_title);
        whatIsCorona_description=findViewById(R.id.whatiscorona_description);
        originOfCorona_title=findViewById(R.id.orginofcorona_title);
        originOfCorona_description=findViewById(R.id.orginofcorona_description);
        coronaHasMedicine_title=findViewById(R.id.coronahasmedicine_title);
        coronaHasMedicine_description=findViewById(R.id.coronahasmedicine_description);
        canWeStopCorona_title=findViewById(R.id.canwestopcorona_title);
        canWeStopCorona_description=findViewById(R.id.canwestopcorona_description);
        whenCoronaSpread_title=findViewById(R.id.whencoronaspread_title);
        whenCoronaSpread_description=findViewById(R.id.whencoronaspread_description);
        retry=findViewById(R.id.retry);
        progressBar=findViewById(R.id.progressBar2);
    }
    public void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<QuerySnapshot> whatIsCorona_Document=firebaseFirestore.collection("Coronavirus").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    List<DocumentSnapshot> documents =task.getResult().getDocuments();
                    if(documents.size()>0){
                    whatIsCorona_title.setText(documents.get(3).getString("title"));
                    whatIsCorona_description.setText(documents.get(3).getString("a").replaceAll("nl","\n"));
                    originOfCorona_title.setText(documents.get(1).getString("title"));
                    originOfCorona_description.setText(documents.get(1).getString("description").replaceAll("nl","\n"));
                    coronaHasMedicine_title.setText(documents.get(2).getString("title"));
                    coronaHasMedicine_description.setText(documents.get(2).getString("description").replaceAll("nl","\n"));
                    canWeStopCorona_title.setText(documents.get(0).getString("title"));
                    canWeStopCorona_description.setText(documents.get(0).getString("description").replaceAll("nl","\n"));
                    whenCoronaSpread_title.setText(documents.get(4).getString("title"));
                    whenCoronaSpread_description.setText(documents.get(4).getString("description").replaceAll("nl","\n"));
                    }
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