package com.ethioptech.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.ethioptech.covid19.models.CallAdapter;
import com.ethioptech.covid19.models.CallModel;

import java.util.ArrayList;

public class CallActivity extends AppCompatActivity {
    ArrayList<CallModel> calls;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        recyclerView=findViewById(R.id.recycler_view);
        calls=new ArrayList<>();
        calls.add(new CallModel("ትግራይ","6244"));
        calls.add(new CallModel("ኦሮሚያ","6955"));
        calls.add(new CallModel("አማራ","6982"));
        calls.add(new CallModel("ደቡብ","6599"));
        calls.add(new CallModel("ድሬደዋ","6407"));
        calls.add(new CallModel("አዲስ አበባ","8335"));
        calls.add(new CallModel("ስለ ኮሮና መረጃ ለማግኘት","444"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CallAdapter(calls,CallActivity.this));
    }
}