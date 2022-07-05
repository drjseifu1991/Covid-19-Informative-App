package com.ethioptech.covid19.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ethioptech.covid19.R;
import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {
    private ArrayList<CallModel> callModelArrayList;
    Context context;
    LayoutInflater layoutInflater;

    public CallAdapter(ArrayList<CallModel> callModelArrayList, Context context) {
        this.callModelArrayList = callModelArrayList;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.call_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.region.setText(callModelArrayList.get(position).getRegionName());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri phone=Uri.parse("tel:" + callModelArrayList.get(position).getPhoneNumber());
                Intent intent=new Intent(Intent.ACTION_DIAL,phone);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return callModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView region;
       ImageButton call;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            region=itemView.findViewById(R.id.phoneNumber_textView);
            call=itemView.findViewById(R.id.call_btn);
        }
    }
}
