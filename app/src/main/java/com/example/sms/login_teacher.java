package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login_teacher extends AppCompatActivity {

    TextInputEditText Email1,Password1;
    MaterialButton Login_teacher1;
    TextView Forgot_password1, New_here_register1;
    ProgressBar Progressbar;
    FirebaseAuth mAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mAuth1 = FirebaseAuth.getInstance();

        Email1 = findViewById(R.id.email_teacher);
        Password1 = findViewById(R.id.password_teacher);
        New_here_register1 = findViewById(R.id.redirect_register_teacher);
        Forgot_password1 = findViewById(R.id.forgot_pass_teacher);
        Progressbar = findViewById(R.id.progressBar3);
        Login_teacher1 = findViewById(R.id.login_btn_teacher);

        Login_teacher1.setOnClickListener(view ->{
            loginTeacher();
        });

        New_here_register1.setOnClickListener(view -> {
            startActivity(new Intent(login_teacher.this,register_teacher.class));
        });

        Forgot_password1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter your Email to receive Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Extract email and sent reset link
                        String mail = resetMail.getText().toString();
                        if(mail.isEmpty())
                        {
                            Toast.makeText(login_teacher.this, "Please Enter Email To Receive Password Reset Link", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mAuth1.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {
                                    Toast.makeText(login_teacher.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(login_teacher.this, "Error ! Reset Link Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

                passwordResetDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the Dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }

    private void loginTeacher() {
        String email = Email1.getText().toString();
        String password = Password1.getText().toString();
        Progressbar = findViewById(R.id.progressBar3);

        if (TextUtils.isEmpty(email)) {
            Email1.setError("Email is Required.");
            Email1.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Password1.setError("Password is Required.");
            Password1.requestFocus();
        } else {
            Progressbar.setVisibility(View.VISIBLE);
            mAuth1.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(login_teacher.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login_teacher.this, teacher_MainActivity.class));
                    } else {
                        Toast.makeText(login_teacher.this, "Login Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}