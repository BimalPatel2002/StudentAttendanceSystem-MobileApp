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

import com.example.sms.navigation.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class login extends AppCompatActivity {

    TextInputEditText Email,Password;
    MaterialButton Login;
    TextView Forgot_password, New_here_register;
    ProgressBar Progressbar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        New_here_register = findViewById(R.id.redirect_register);
        Forgot_password = findViewById(R.id.forgot_pass);
        Progressbar = findViewById(R.id.progressBar1);
        Login = findViewById(R.id.login_btn);

        Login.setOnClickListener(view ->{
            loginUser();
        });

        New_here_register.setOnClickListener(view -> {
            startActivity(new Intent(login.this,register.class));
        });

        Forgot_password.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(login.this, "Please Enter Email To Receive Password Reset Link", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {
                                    Toast.makeText(login.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(login.this, "Error ! Reset Link Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void loginUser() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        Progressbar = findViewById(R.id.progressBar1);

        if (TextUtils.isEmpty(email)) {
            Email.setError("Email is Required.");
            Email.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Password.setError("Password is Required.");
            Password.requestFocus();
        } else {
            Progressbar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, MainActivity.class));
                    } else {
                        Toast.makeText(login.this, "Login Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}