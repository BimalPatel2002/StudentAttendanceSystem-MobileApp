package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    TextInputEditText FullName,Email,RfidNo,Standard,Phone,RollNo,Division,Password,ConfirmPassword;
    CountryCodePicker CCP;
    MaterialButton Register;
    TextView Already_user_login;
    ProgressBar Progressbar;
    FirebaseAuth mAuth;

    DatabaseReference StudentDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        FullName = findViewById(R.id.full_name);
        Email = findViewById(R.id.email);
        RfidNo = findViewById(R.id.rfid_no);
        Standard = findViewById(R.id.std);
        Division = findViewById(R.id.div);
        CCP = findViewById(R.id.ccp);
        Phone = findViewById(R.id.phone);
        RollNo = findViewById(R.id.roll);
        Password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirm_password);
        Already_user_login = findViewById(R.id.already_user_login);
        Progressbar = findViewById(R.id.progressBar2);
        Register = findViewById(R.id.register_btn);

        mAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(view->{
            createUser();
        });

        Already_user_login.setOnClickListener(view -> {
            startActivity(new Intent(register.this,login.class));
        });

        StudentDbRef = FirebaseDatabase.getInstance().getReference().child("students_registration_data");
    }

        public void createUser(){
        String fullname = FullName.getText().toString();
        String email = Email.getText().toString();
        String code = CCP.getSelectedCountryCode();
        String phone = Phone.getText().toString();
        String rfidno = RfidNo.getText().toString();
        String standard = Standard.getText().toString();
        String division = Division.getText().toString();
        String rollno= RollNo.getText().toString();
        String password = Password.getText().toString();
        String confirmpassword = ConfirmPassword.getText().toString();
        Progressbar = findViewById(R.id.progressBar2);

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
//        Pattern pattern1 = Pattern.compile(".[a-zA-Z0-9+._%-+]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "." + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}.");
        if (TextUtils.isEmpty(fullname)) {
            FullName.setError("Fullname is Required.");
            FullName.requestFocus();
        }else if (TextUtils.isEmpty(email)) {
            Email.setError("Email is Required.");
            Email.requestFocus();
        }else if (! pattern.matcher(email).matches()) {
            Email.setError("Invalid Email Address.");
            Email.requestFocus();
        }else if (TextUtils.isEmpty(phone)) {
            Phone.setError("Phone number is Required.");
            Phone.requestFocus();
        } else if (TextUtils.isEmpty(rfidno)) {
            RfidNo.setError("RfidNo is Required.");
            RfidNo.requestFocus();
        }else if (TextUtils.isEmpty(standard)) {
            Standard.setError("Standard is Required.");
            Standard.requestFocus();
        }else if (standard.length() > 12) {
            Standard.setError("Please enter Valid Standard.");
            Standard.requestFocus();
        }else if (TextUtils.isEmpty(division)) {
            Division.setError("Division is Required.");
            Division.requestFocus();
        }else if (TextUtils.isEmpty(rollno)) {
            RollNo.setError("Rollno is Required.");
            RollNo.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            Password.setError("Password is Required.");
            Password.requestFocus();
        }else if (password.length() < 6) {
            Password.setError("Password must be more than 6 characters");
            Password.requestFocus();
        }else if (! password.matches(".*[A-Z].*")) {
            Password.setError("Password must contain 1 Upper Case.");
            Password.requestFocus();
        }else if (! password.matches(".*[a-z].*")) {
            Password.setError("Password must contain 1 Lower Case.");
            Password.requestFocus();
        }else if (! password.matches(".*[@#_$^+&*].*")) {
            Password.setError("Password must contain 1 special character.");
            Password.requestFocus();
        }else if (!password.equals(confirmpassword)) {
            ConfirmPassword.setError("Password didn't match.");
            ConfirmPassword.requestFocus();
        } else{
            Progressbar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                           Students students = new Students(fullname, email, code, phone, rfidno, standard, division, rollno, password);
                           StudentDbRef.child(rfidno).setValue(students);
                           Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(register.this, login.class));
                    } else {
                        Toast.makeText(register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
