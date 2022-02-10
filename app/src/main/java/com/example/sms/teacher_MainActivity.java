package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class teacher_MainActivity extends AppCompatActivity {

    MaterialButton Button1,Button2,Logout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        Button1 = findViewById(R.id.add);
        Button2 = findViewById(R.id.view_attendance);
        Logout = findViewById(R.id.logout);

        Button1.setOnClickListener(view -> {
            Toast.makeText(teacher_MainActivity.this, "Add Attendance", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(teacher_MainActivity.this, teacher.class));
        });

        Button2.setOnClickListener(View -> {
            Toast.makeText(teacher_MainActivity.this, "View Attendance List", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(teacher_MainActivity.this,View_attendanceList.class));
        });

        mAuth = FirebaseAuth.getInstance();

        Logout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(teacher_MainActivity.this, login_teacher.class));
        });
    }
}