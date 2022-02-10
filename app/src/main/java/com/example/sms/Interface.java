package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sms.navigation.MainActivity;
import com.google.android.material.button.MaterialButton;

public class Interface extends AppCompatActivity {

    MaterialButton Button1,Button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        Button1 = findViewById(R.id.button1);
        Button2 = findViewById(R.id.button2);

        Button1.setOnClickListener(view -> {
            Toast.makeText(Interface.this, "Parents Interface", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Interface.this, MainActivity.class));
        });

        Button2.setOnClickListener(View -> {
            Toast.makeText(Interface.this, "Teachers Interface", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Interface.this, login_teacher.class));
        });
    }
}