package com.ethioptech.covid19;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethioptech.covid19.models.QuestionModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
public class QuestionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FirebaseFirestore firestore;
    FirestoreRecyclerAdapter<QuestionModel,ViewHolder> firestoreRecyclerAdapter;
    FirestoreRecyclerOptions<QuestionModel> firestoreRecyclerOptions;
    Query query;
    private ArrayList<QuestionModel> questionModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        recyclerView=findViewById(R.id.recycler_view);
        questionModelArrayList=new ArrayList<>();
        firestore=FirebaseFirestore.getInstance();
        query=firestore.collection("questions");
        firestoreRecyclerOptions=new FirestoreRecyclerOptions.Builder<QuestionModel>().setQuery(query,QuestionModel.class).build();
        firestoreRecyclerAdapter=new FirestoreRecyclerAdapter<QuestionModel, ViewHolder>(firestoreRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull final QuestionModel model) {
                holder.question.setText(model.getQuestion());
                questionModelArrayList.add(new QuestionModel(model.getQuestion(),model.getAnswer()));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder answer=new AlertDialog.Builder(QuestionActivity.this);
                        answer.setTitle(questionModelArrayList.get(position).getQuestion());
                        answer.setMessage(questionModelArrayList.get(position).getAnswer());
                        AlertDialog alertDialog=answer.create();
                        alertDialog.show();
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_item,parent,false);
                return new ViewHolder(view);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(firestoreRecyclerAdapter);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question_text_view);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firestoreRecyclerAdapter!=null){
            firestoreRecyclerAdapter.stopListening();
        }
    }
}
