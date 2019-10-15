package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class found extends AppCompatActivity {
    private EditText object,place,desc,color,no;
    private DatePicker date;
    private TimePicker time;
    private Button back;
    private ProgressDialog pd;

    private FirebaseAuth auth;
    private int notificationId=1;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @RequiresApi(api = Build.VERSION_CODES.M)


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);
        auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        object=(EditText)findViewById(R.id.object);
        place=(EditText)findViewById(R.id.color);
        desc=(EditText)findViewById(R.id.description);
        date=(DatePicker)findViewById(R.id.date);
        final TimePicker time=(TimePicker)findViewById(R.id.time);
        color=(EditText)findViewById(R.id.color);
        no=(EditText)findViewById(R.id.phone);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                auth= FirebaseAuth.getInstance();
                String Objectt=object.getText().toString().trim();
                String placet=place.getText().toString().trim();
                String desct= desc.getText().toString().trim();
                String colort =color.getText().toString().trim();
                String not =no.getText().toString().trim();

                Integer hour= time.getHour();
                Integer min=time.getMinute();
                String timet= hour.toString()+":"+min.toString();

                Integer day=date.getDayOfMonth();
                Integer month=date.getMonth();
                Integer month1=month+1;
                Integer year=date.getYear();
                final String datet=day.toString()+"-"+month1.toString()+"-"+year.toString();
                //  String userid = auth.getCurrentUser().getUid();
                final DocumentReference noteRef=db.collection("found").document();
                if(Objectt.isEmpty())
                { object.requestFocus();
                    Toast.makeText(found.this,"object is empty",Toast.LENGTH_SHORT).show();

                }
                else if(placet.isEmpty())
                {place.requestFocus();
                    Toast.makeText(found.this,"place is empty",Toast.LENGTH_SHORT).show();

                }
                else if(colort.isEmpty())
                {color.requestFocus();
                    Toast.makeText(found.this,"color of object is empty",Toast.LENGTH_SHORT).show();

                }
                else if(desct.isEmpty())
                {desc.requestFocus();
                    Toast.makeText(found.this,"Description of object is empty",Toast.LENGTH_SHORT).show();

                }
                else if(timet.isEmpty())
                {time.requestFocus();
                    Toast.makeText(found.this,"time of object you found is empty",Toast.LENGTH_SHORT).show();

                }
                else if(datet.isEmpty())
                {date.requestFocus();
                    Toast.makeText(found.this,"Date on which you found the object is empty",Toast.LENGTH_SHORT).show();

                }
                else if(not.isEmpty())
                {no.requestFocus();
                    Toast.makeText(found.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Record rec=new Record( placet,Objectt,colort,not,datet,timet,desct);


                    noteRef.set(rec);


                    noteRef.set(rec).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(found.this, " Information is store Successfully", Toast.LENGTH_SHORT).show();
                            Intent in =new Intent(found.this,landf.class);
                            startActivity(in);


                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(found.this, "Failed to store  Information", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                //pd.setMessage("storing");
                //pd.show();
            }
        });

    }
}