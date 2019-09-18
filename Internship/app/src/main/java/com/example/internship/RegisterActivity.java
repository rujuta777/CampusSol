package com.example.internship;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class RegisterActivity extends AppCompatActivity
{
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    private ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();

        final EditText name,school , add, mobile,parent_mobile,email,want_bacome,refer,enquiry;
        name=(EditText)findViewById(R.id.name);
        school=(EditText)findViewById(R.id.school);
        add=(EditText)findViewById(R.id.add);
        mobile=(EditText)findViewById(R.id.mobile);
        parent_mobile=(EditText)findViewById(R.id.parent_mobile);
        email = (EditText) findViewById(R.id.email_get);
        want_bacome=(EditText)findViewById(R.id.want_become);
        refer=(EditText)findViewById(R.id.refer_by);
        enquiry=(EditText)findViewById(R.id.enquiry);
        final EditText date=(EditText)findViewById(R.id.cur_date);
        Button reg=(Button)findViewById(R.id.register);
       // final DatePicker join_date=(DatePicker)findViewById(R.id.datepicker) ;


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name=name.getText().toString().trim();
                final String School=school.getText().toString().trim();
                final String Add=add.getText().toString().trim();
                final String Mobile=mobile.getText().toString().trim();
                final String Parent_mobile=parent_mobile.getText().toString().trim();
                final String Email=email.getText().toString().trim();
                final String Want_become=want_bacome.getText().toString().trim();
                final String Refer=refer.getText().toString().trim();
                final String Enquiry=enquiry.getText().toString().trim();
                final String Join_date=date.getText().toString().trim();
                final String Pass="1234567";
          /*      Integer day=join_date.getDayOfMonth();
                Integer month=join_date.getMonth();
                Integer month1=month+1;
                Integer year=join_date.getYear();
                final String Join_date=day.toString()+"-"+month1.toString()+"-"+year.toString();*/

                if(Name.isEmpty())
                {
                    name.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Name Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(School.isEmpty())
                {
                    school.requestFocus();
                    Toast.makeText(RegisterActivity.this, "School block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Add.isEmpty())
                {
                    add.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Address No Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Mobile.isEmpty())
                {
                    mobile.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Mobile Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Parent_mobile.isEmpty())
                {
                    parent_mobile.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Email.isEmpty())
                {
                    email.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Email Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Want_become.isEmpty())
                {
                    want_bacome.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Refer.isEmpty())
                {
                    refer.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Join_date.isEmpty())
                {
                    date.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Enquiry.isEmpty())
                {
                    enquiry.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final DocumentReference noteRef=db.collection("Student").document();
                    auth.createUserWithEmailAndPassword(Email,Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Registration failed...", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                        Record1 record=new Record1( Name,School, Add, Mobile,Parent_mobile,Email,Pass,Want_become,Refer,Join_date,Enquiry);
                                        noteRef.set(record);
                                        noteRef.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(RegisterActivity.this, "Information is store Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, "Failed to store Information", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Toast.makeText(RegisterActivity.this, "Registration Successful...", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(RegisterActivity.this,MainActivity.class);
                                        startActivity(i);

                                    }
                                }
                            });
                }

            }
        });
    }
}
/*

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class RegisterActivity extends AppCompatActivity
{
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    private ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        final EditText namet, classt, divt, rollt, passt, mobilet;
        namet=(EditText)findViewById(R.id.name);
        classt=(EditText)findViewById(R.id.class1);
        divt=(EditText)findViewById(R.id.division);
        rollt=(EditText)findViewById(R.id.roll);
        passt=(EditText)findViewById(R.id.department);
        mobilet=(EditText)findViewById(R.id.mobile);
        final EditText emailt = (EditText) findViewById(R.id.email);
        Button reg=(Button)findViewById(R.id.reg_button);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=namet.getText().toString().trim();
                final String class1=classt.getText().toString().trim();
                final String div=divt.getText().toString().trim();
                final String roll=rollt.getText().toString().trim();
                final String Pass=passt.getText().toString().trim();
                final String mobile=mobilet.getText().toString().trim();
                final String email=emailt.getText().toString().trim();
                final DocumentReference noteRef=db.collection("Student").document(name);
                if(name.isEmpty())
                {
                    namet.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Name Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(class1.isEmpty())
                {
                    classt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Class block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(div.isEmpty())
                {
                    divt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Division Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(roll.isEmpty())
                {
                    rollt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Roll No Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.isEmpty())
                {
                    passt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(mobile.isEmpty())
                {
                    mobilet.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Mobile Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty())
                {
                    emailt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Email Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Registration falied...", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Record1 record=new Record1( name,class1,div,roll, Pass, mobile,email);
                                        noteRef.set(record);
                                        noteRef.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(RegisterActivity.this, "Information is store Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, "Failed to store Information", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Toast.makeText(RegisterActivity.this, "Registration Successful...", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(RegisterActivity.this,MainActivity.class);
                                        startActivity(i);

                                    }
                                }
                            });
                }

            }
        });
    }
}
 */
/*
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".RegisterActivity">
    <LinearLayout
        android:orientation="vertical"
        android:layout_width="wrap_content"
        android:layout_height="match_parent">


        <TextView
            android:id="@+id/textView2"
            android:layout_width="374dp"
            android:layout_height="56dp"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="12dp"
            android:text="@string/reg"
            android:textSize="30sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
    </LinearLayout>


</ScrollView>
 */
/*
 <DatePicker
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/datepicker"
            />
             <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/date_add"
            android:text="Join Date"
            android:textSize="25dp"
            android:hint="Date"
            android:inputType="date"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.65"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.907" />

        />
 */