package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class teacher extends AppCompatActivity {

    TextInputEditText FullName,Standard,RollNo,Division,RfidNo;
    MaterialButton Add_Attendance;

    FirebaseAuth mAuth;

    DatabaseReference DbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Objects.requireNonNull(getSupportActionBar()).hide();

        FullName = findViewById(R.id.full_name);
        Standard = findViewById(R.id.std);
        RollNo = findViewById(R.id.roll);
        Division = findViewById(R.id.div);
        RfidNo = findViewById(R.id.rfid);
        Add_Attendance = findViewById(R.id.add_attendance);

        mAuth = FirebaseAuth.getInstance();

        Add_Attendance.setOnClickListener(view-> addUser());

        DbRef = FirebaseDatabase.getInstance().getReference().child("Attendance");

    }

    public void addUser(){
        String fullname = FullName.getText().toString();
        String standard = Standard.getText().toString();
        String division= Division.getText().toString();
        String rollno= RollNo.getText().toString();
        String rfidno= RfidNo.getText().toString();


        if (TextUtils.isEmpty(fullname)) {
            FullName.setError("FullName is Required.");
            FullName.requestFocus();
        }else if (TextUtils.isEmpty(standard)) {
            Standard.setError("Standard is Required.");
            Standard.requestFocus();
        }else if (TextUtils.isEmpty(division)) {
            Division.setError("Division is Required.");
            Standard.requestFocus();
        }else if (TextUtils.isEmpty(rollno)) {
            RollNo.setError("Rollno is Required.");
            RollNo.requestFocus();
        }else if (TextUtils.isEmpty(rfidno)) {
            RfidNo.setError("Rfidno is required.");
            RfidNo.requestFocus();
        }else{
            AddAttendance students = new AddAttendance(fullname, standard, division, rollno, rfidno);
            DbRef.push().setValue(students);
            Toast.makeText(teacher.this, "Attendance inserted", Toast.LENGTH_SHORT).show();
            FullName.requestFocus();
        }
    }

}