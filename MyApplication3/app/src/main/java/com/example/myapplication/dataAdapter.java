package com.example.myapplication;


import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder>
{
    public List<Record>recordList;
    private OnNoteListner onNoteListner;
    public dataAdapter(List<Record>trecord){
        this.recordList=trecord;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements DialogInterface.OnClickListener {


        View mview;
        TextView object,place,color,desc,time,date,no;
        OnNoteListner onNoteListner;
        public ViewHolder(@NonNull View itemView/* ,OnNoteListner onNoteListnerthis*/) {
            super(itemView);
            mview=itemView;
            object=mview.findViewById(R.id.object);
            place=mview.findViewById(R.id.place);
            color=mview.findViewById(R.id.color);
            desc=mview.findViewById(R.id.description);
            no=mview.findViewById(R.id.phone);
            time=mview.findViewById(R.id.time);
            date=mview.findViewById(R.id.date);
//            this.onNoteListner=onNoteListnerthis;


            //itemView.setOnClickListener((View.OnClickListener) this);

        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            onNoteListner.onNoteClick(getAdapterPosition());

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.object.setText(recordList.get(position).getObject());
        holder.place.setText(recordList.get(position).getPlacet());
        holder.color.setText(recordList.get(position).getColort());
        holder.desc.setText(recordList.get(position).getDesct());
        holder.time.setText(recordList.get(position).getTimet());
        holder.date.setText(recordList.get(position).getDatet());
        /// holder.no.setText(recordList.get(position).getnot());
    }



    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public interface OnNoteListner{
        void onNoteClick(int position);

    }


}