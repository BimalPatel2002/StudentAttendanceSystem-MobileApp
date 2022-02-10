package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.Objects;
import java.util.regex.Pattern;

public class register_teacher extends AppCompatActivity {

    TextInputEditText FullName1,Email1,Phone1,Password1,ConfirmPassword1;
    CountryCodePicker CCP1;
    MaterialButton Register1;
    TextView Already_user_login1;
    ProgressBar Progressbar;
    FirebaseAuth mAuth1;

    DatabaseReference StudentDbRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        Objects.requireNonNull(getSupportActionBar()).hide();

        FullName1 = findViewById(R.id.full_name_teacher);
        Email1 = findViewById(R.id.email_teacher);
        CCP1 = findViewById(R.id.ccp1);
        Phone1 = findViewById(R.id.phone_teacher);
        Password1 = findViewById(R.id.password_teacher);
        ConfirmPassword1 = findViewById(R.id.confirm_password_teacher);
        Already_user_login1 = findViewById(R.id.already_user_login_teacher);
        Progressbar = findViewById(R.id.progressBar4);
        Register1 = findViewById(R.id.register_btn_teacher);

        mAuth1 = FirebaseAuth.getInstance();

        Register1.setOnClickListener(view-> createTeacher());

        Already_user_login1.setOnClickListener(view -> startActivity(new Intent(register_teacher.this,login_teacher.class)));
        StudentDbRef1 = FirebaseDatabase.getInstance().getReference().child("Teachers_registration_data");
    }

    public void createTeacher(){
        String fullname = FullName1.getText().toString();
        String email = Email1.getText().toString();
        String code = CCP1.getSelectedCountryCode();
        String phone = Phone1.getText().toString();
        String password = Password1.getText().toString();
        String confirmpassword = ConfirmPassword1.getText().toString();
        Progressbar = findViewById(R.id.progressBar4);

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

        if (TextUtils.isEmpty(fullname)) {
            FullName1.setError("Fullname is Required.");
            FullName1.requestFocus();
        }else if (TextUtils.isEmpty(email)) {
            Email1.setError("Email is Required.");
            Email1.requestFocus();
        }else if (! pattern.matcher(email).matches()) {
            Email1.setError("Invalid Email Address.");
            Email1.requestFocus();
        }else if (TextUtils.isEmpty(phone)) {
            Phone1.setError("Phone number is Required.");
            Phone1.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            Password1.setError("Password is Required.");
            Password1.requestFocus();
        }else if (password.length() < 6) {
            Password1.setError("Password must be more than 6 characters");
            Password1.requestFocus();
        }else if (! password.matches(".*[A-Z].*")) {
            Password1.setError("Password must contain 1 Upper Case.");
            Password1.requestFocus();
        }else if (! password.matches(".*[a-z].*")) {
            Password1.setError("Password must contain 1 Lower Case.");
            Password1.requestFocus();
        }else if (! password.matches(".*[@#_$^+&*].*")) {
            Password1.setError("Password must contain 1 special character.");
            Password1.requestFocus();
        }else if (TextUtils.isEmpty(confirmpassword)) {
            ConfirmPassword1.setError("Confirm Password is Required.");
            ConfirmPassword1.requestFocus();
        }else if (!password.equals(confirmpassword)) {
            ConfirmPassword1.setError("Password didn't match.");
            ConfirmPassword1.requestFocus();
        } else{
            Progressbar.setVisibility(View.VISIBLE);
            mAuth1.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {

                    Teachers t = new Teachers(fullname,email,code,phone,password);
                    StudentDbRef1.push().setValue(t);
                    Toast.makeText(register_teacher.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(register_teacher.this, login_teacher.class));
                } else {
                    Toast.makeText(register_teacher.this, "Error !" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}