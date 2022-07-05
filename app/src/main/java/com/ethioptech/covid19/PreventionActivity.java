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

public class PreventionActivity extends AppCompatActivity {
    private TextView cleanTextView;
    private TextView coverMouthTextView;
    private TextView eat_textView;
    private TextView shakeHand_textView;
    private TextView touchFace_textView;
    private TextView cleanMobile_textView;
    private TextView keepDistance_textView;
    private TextView oneMeter_textView;
    private TextView pc_textView;
    private TextView socialDistance_textView;
    private TextView washHand_textView;
    FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private TextView retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
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
        cleanTextView=findViewById(R.id.clean_textView);
        coverMouthTextView=findViewById(R.id.coverMouth_textView);
        eat_textView=findViewById(R.id.eat_textView);
        shakeHand_textView=findViewById(R.id.shakehand_textView);
        touchFace_textView=findViewById(R.id.touchFace_textView);
        cleanMobile_textView=findViewById(R.id.cleanMobile_textView);
        keepDistance_textView=findViewById(R.id.keepDistance_textView);
        oneMeter_textView=findViewById(R.id.oneMeter_textView);
        pc_textView=findViewById(R.id.pc_textView);
        socialDistance_textView=findViewById(R.id.socialDistance_textView);
        washHand_textView=findViewById(R.id.washhand_textView);
        progressBar=findViewById(R.id.progressBar2);
        retry=findViewById(R.id.retry);
    }
    public  void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> preventionDocument=firebaseFirestore.collection("preventionmethod").document("prevention").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                cleanTextView.setText(documentSnapshot.getString("cleanenviroment").replaceAll("nl","\n"));
                coverMouthTextView.setText(documentSnapshot.getString("covermouth").replaceAll("nl","\n"));
                eat_textView.setText(documentSnapshot.getString("donoteatrow").replaceAll("nl","\n"));
                shakeHand_textView.setText(documentSnapshot.getString("donotshakehandorkiss").replaceAll("nl","\n"));
                touchFace_textView.setText(documentSnapshot.getString("donottouchface").replaceAll("nl","\n"));
                cleanMobile_textView.setText(documentSnapshot.getString("howcleanmobile").replaceAll("nl","\n"));
                keepDistance_textView.setText(documentSnapshot.getString("keepdistancefevercough").replaceAll("nl","\n"));
                oneMeter_textView.setText(documentSnapshot.getString("onemeteraway").replaceAll("nl","\n"));
                pc_textView.setText(documentSnapshot.getString("pc").replaceAll("nl","\n"));
                socialDistance_textView.setText(documentSnapshot.getString("socialdistancing").replaceAll("nl","\n"));
                washHand_textView.setText(documentSnapshot.getString("washhand").replaceAll("nl","\n"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"please Connect to internet",Toast.LENGTH_SHORT).show();
            }
        });
    }
}