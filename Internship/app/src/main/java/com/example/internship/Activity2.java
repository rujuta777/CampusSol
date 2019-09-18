package com.example.internship;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    private static final String TAG ="Something" ;
    //private FirebaseAuth auth;
    DocumentReference documentReference;
    private FirebaseFirestore db;

    DataAdapter mdataAdapter;
    RecyclerView rvlist;
    List<Record1>userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userlist=new ArrayList<>();
        mdataAdapter=new DataAdapter(userlist);
        setContentView(R.layout.activity_2);
        db=FirebaseFirestore.getInstance();
        documentReference= db.collection("Student").document();
        // auth= FirebaseAuth.getInstance();
        rvlist=findViewById(R.id.rvuserlist);

        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(mdataAdapter);
        db.collection("Student").addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                {
                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {

                        Record1 record=dc.getDocument().toObject(Record1.class);
                        userlist.add(record);

                        mdataAdapter.notifyDataSetChanged();
                        rvlist.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Activity2.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });
        (findViewById(R.id.download_data_item)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,Answer_activity.class);
                startActivity(intent);
            }
        });

    }
}