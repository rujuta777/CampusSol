package com.example.internship_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ImageButton admin=(ImageButton)findViewById(R.id.admin);
        ImageButton user=(ImageButton)findViewById(R.id.admin);
        ImageButton faculty=(ImageButton)findViewById(R.id.admin);


        final Button login=(Button)findViewById(R.id.signin);
        final Button register=(Button)findViewById(R.id.register);
        final TextView forget=(TextView)findViewById(R.id.forget);
        EditText username=(EditText)findViewById(R.id.username);
        EditText pass=(EditText)findViewById(R.id.pass);

        admin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                login.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Activity2.this, Admin.class);
                        startActivity(i);
                    }
                });
                register.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent i = new Intent(Activity2.this, Register.class);
                        startActivity(i);
                    }
                });

            }
        });

        user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                login.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Activity2.this,User.class);
                        startActivity(i);
                    }
                });
                register.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent i = new Intent(Activity2.this, Register.class);
                        startActivity(i);
                    }
                });

            }
        });
        faculty.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                login.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Activity2.this, Faculty.class);
                        startActivity(i);
                    }
                });
                register.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent i = new Intent(Activity2.this, Register.class);
                        startActivity(i);
                    }
                });

            }
        });


        forget.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Activity2.this, Activity2.class);
                startActivity(i);
            }
        });
    }
}
