package com.example.internship;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
{
    public List<Record1>record2List;
    public DataAdapter(List<Record1>trecord2){
        this.record2List=trecord2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView topic,name,conduct,venue,time,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            name=mview.findViewById(R.id.name_download_data);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.download_data,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(record2List.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return record2List.size();
    }
}
/*
    <TextView
        android:id="@+id/name_download_data"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_alignParentStart="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="13dp"
        android:layout_marginLeft="13dp"
        android:layout_marginTop="35dp"
        android:textSize="25sp" />

 */
/*
 /*   @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.download_data,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(record2List.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return record2List.size();
    }*/
