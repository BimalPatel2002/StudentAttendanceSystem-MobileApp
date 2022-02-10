package com.example.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Model> arrayList;
    Context context;

    public Adapter(ArrayList<Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model model = arrayList.get(position);
        holder.division.setText(model.getDivision());
        holder.fullname.setText(model.getFullname());
        holder.rollno.setText(model.getRollno());
        holder.standard.setText(model.getStandard());
        holder.rfidno.setText(model.getRfidno());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView division,fullname,rollno,standard,rfidno,date,time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            division = itemView.findViewById(R.id.division);
            fullname = itemView.findViewById(R.id.fullname);
            rollno = itemView.findViewById(R.id.rollno);
            standard = itemView.findViewById(R.id.standard);
            rfidno = itemView.findViewById(R.id.rfidno);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
