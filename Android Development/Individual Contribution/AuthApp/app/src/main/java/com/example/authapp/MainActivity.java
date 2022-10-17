package com.example.authapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail,editTextPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    private TextView textViewRegisterUser,textViewInvalidCredentials,textViewForgotPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail=findViewById(R.id.loginPageEmailEditText);
        editTextPassword=findViewById(R.id.loginPagePasswordEditText);
        textViewInvalidCredentials=findViewById(R.id.textViewInvalidCredentials);
        progressBar=findViewById(R.id.mainActivityIndeterminateProgressbar);

        textViewRegisterUser=findViewById(R.id.textViewRegisterUser);
        textViewRegisterUser.setOnClickListener(this);

        textViewForgotPassword=findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(this);

        buttonLogin=findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(this);

        mAuth=FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.textViewRegisterUser:
                Intent intent=new Intent(getApplicationContext(),RegisterUser.class);
                startActivity(intent);
                break;

            case R.id.login_button:
                loginUser();
                textViewInvalidCredentials.setVisibility(View.GONE);
                break;

            case R.id.textViewForgotPassword:
                startActivity(new Intent(MainActivity.this,ForgotPassword.class));
                break;

        }
    }

    public void loginUser(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Enter e-mail");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Invalid e-mail address");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Enter Password");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError("Password should be more than 8 characters long");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            if(user.isEmailVerified()) {
                                startActivity(new Intent(getApplicationContext(), ProfileViewUser.class));
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Check your email to verify email address", Toast.LENGTH_LONG).show();
                                user.sendEmailVerification();
                            }

                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            textViewInvalidCredentials.setVisibility(View.VISIBLE);
                            return;
                        }
                    }
                });

    }
}