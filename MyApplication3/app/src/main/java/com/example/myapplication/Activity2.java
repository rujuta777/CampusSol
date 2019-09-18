package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Edits;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Nullable;

public class Activity2 extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    String emailt;
    TextView textdisplay;
    List<Record1> userlist;
    String display, display2, roll;
    Double avg;
    Button pro;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        auth=FirebaseAuth.getInstance();
        final Button att=(Button)findViewById(R.id.attendance_but);
        Button lost=(Button)findViewById(R.id.lost_but);
        Button event=(Button)findViewById(R.id.event_but);
        Button back=(Button)findViewById(R.id.back);
        pro=(Button)findViewById(R.id.myprofile);
        textdisplay=(TextView)findViewById(R.id.textDisplay);

        Intent i=getIntent();
        emailt=i.getStringExtra("emailt");
        db.collection("Student")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {


                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                        for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                        {
                            //Toast.makeText(Activity2.this, "Inside For Loop", Toast.LENGTH_SHORT).show();
                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {

                                Record1 record=dc.getDocument().toObject(Record1.class);
                                if(record.getEmailt().equals(emailt)){
                                    //Toast.makeText(Activity2.this, "Inside If Block", Toast.LENGTH_SHORT).show();
                                    display = "Name :  " + record.getNamet() + "\n" + "Roll_No : " + record.getRollt();
                                    roll=record.getRollt();
                                }
                            }
                        }
                        textdisplay.setText(display);
                    }
                });

        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,Attendance.class);
                startActivity(intent);
            }
        });


        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,EventActivity.class);
                startActivity(intent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Attendance")
                        .addSnapshotListener(new EventListener<QuerySnapshot>()
                        {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e)
                            {
                                Double sum = 0.0;
                                Double total = 0.0;
                                for(DocumentChange dc: queryDocumentSnapshots.getDocumentChanges())
                                {
                                    total+=1.0;
                                    if(dc.getType()==DocumentChange.Type.ADDED)
                                    {
                                        Map<String, Object> attend = dc.getDocument().getData();
                                        for (Map.Entry<String, Object> stringObjectEntry : attend.entrySet())
                                        {
                                            if (stringObjectEntry.getKey().contains(roll))
                                            {
                                                if (stringObjectEntry.getValue().equals("P"))
                                                {
                                                    sum += 1.0;
                                                }
                                            }
                                        }
                                    }
                                }
                                avg = (sum / total) * 100;
                                display2=display+"\n"+avg.toString()+ '%';
                            }
                        });
                textdisplay.setText(display2);
            }
        });

    }

    protected void onResume() {

        super.onResume();
        db.collection("Student")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {


                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                        for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                        {
                            //Toast.makeText(Activity2.this, "Inside For Loop", Toast.LENGTH_SHORT).show();
                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {

                                Record1 record=dc.getDocument().toObject(Record1.class);
                                if(record.getEmailt().equals(emailt)){
                                    //Toast.makeText(Activity2.this, "Inside If Block", Toast.LENGTH_SHORT).show();
                                    display = "Name :  " + record.getNamet() + "\n" + "Roll_No : " + record.getRollt();
                                    roll=record.getRollt();
                                }
                            }
                        }
                        textdisplay.setText(display);
                    }
                });

        db.collection("Attendance")
                .addSnapshotListener(new EventListener<QuerySnapshot>()
                {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e)
                    {
                        Double sum = 0.0;
                        Double total = 0.0;
                        for(DocumentChange dc: queryDocumentSnapshots.getDocumentChanges())
                        {
                            total+=1.0;
                            if(dc.getType()==DocumentChange.Type.ADDED)
                            {
                                Map<String, Object> attend = dc.getDocument().getData();
                                for (Map.Entry<String, Object> stringObjectEntry : attend.entrySet())
                                {
                                    if (stringObjectEntry.getKey().contains(roll))
                                    {
                                        if (stringObjectEntry.getValue().equals("P"))
                                        {
                                            sum += 1.0;
                                        }
                                    }
                                }
                            }
                        }
                        avg = (sum / total) * 100;
                        display2=display+"\n"+avg.toString()+ '%';
                    }
                });
        textdisplay.setText(display2);
}
}


/*

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Edits;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Nullable;

public class Activity2 extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    String emailt;
    TextView textdisplay;
    List<Record1> userlist;
    String display, roll;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        auth=FirebaseAuth.getInstance();
        final Button att=(Button)findViewById(R.id.attendance_but);
        Button lost=(Button)findViewById(R.id.lost_but);
        Button event=(Button)findViewById(R.id.event_but);
        Button back=(Button)findViewById(R.id.back);
        Button pro=(Button)findViewById(R.id.myprofile);
        textdisplay=(TextView)findViewById(R.id.textDisplay);
        //Toast.makeText(this, "Before", Toast.LENGTH_SHORT).show();

      //  Toast.makeText(this, "After", Toast.LENGTH_SHORT).show();
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,Attendance.class);
                startActivity(intent);
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,EventActivity.class);
                startActivity(intent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=getIntent();
                emailt=i.getStringExtra("emailt");
                db.collection("Student")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {


                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                        for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                        {
                            //Toast.makeText(Activity2.this, "Inside For Loop", Toast.LENGTH_SHORT).show();
                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {

                                Record1 record=dc.getDocument().toObject(Record1.class);
                                if(record.getEmailt().equals(emailt)){
                                    //Toast.makeText(Activity2.this, "Inside If Block", Toast.LENGTH_SHORT).show();
                                    display = "Name :  " + record.getNamet() + "\n" + "Roll_No : " + record.getRollt();
                                    roll=record.getRollt();
                                }
                            }
                        }
                    }
                });
                db.collection("Attendance")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                Double sum = 0.0;
                                Double total = 0.0;
                                for(DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()){
                                    total+=1.0;
                                    if(dc.getType()==DocumentChange.Type.ADDED){
                                        Map<String, Object> attend = dc.getDocument().getData();
                                        for (Map.Entry<String, Object> stringObjectEntry : attend.entrySet()) {
                                            //Map.Entry myvar = (Map.Entry) stringObjectEntry;
                                            //String ch = stringObjectEntry.getKey();
                                            //Toast.makeText(Activity2.this, ch, Toast.LENGTH_SHORT).show();
                                            if (stringObjectEntry.getKey().contains(roll)) {
                                                if (stringObjectEntry.getValue().equals("P")) {
                                                    Toast.makeText(Activity2.this, "Inside If...", Toast.LENGTH_SHORT).show();
                                                    sum += 1.0;
                                                }
                                            }
                                        }
                                    }
                                }
                                Double avg = (sum / total) * 100;
                                display=display+"\n"+avg.toString()+"%";
                                textdisplay.setText(display);
                            }
                        });
            }
        });

    }
}

 */