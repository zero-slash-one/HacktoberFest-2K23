package com.example.authapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText editTextName,editTextUsername,editTextEmail,editTextPassword,editTextAge;
    private Button buttonRegister,buttonReset;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();



        buttonRegister=(Button) findViewById(R.id.registerButton);
        buttonReset=(Button) findViewById(R.id.resetButton);
        editTextName=(EditText) findViewById(R.id.edit_text_name);
        editTextUsername=(EditText) findViewById(R.id.edit_text_username);
        editTextEmail=(EditText) findViewById(R.id.edit_text_email);
        editTextPassword=(EditText) findViewById(R.id.edit_text_password);
        editTextAge=(EditText) findViewById(R.id.edit_text_age);
        progressBar=(ProgressBar) findViewById(R.id.registerUserIndeterminateProgressbar);

        buttonRegister.setOnClickListener(this);
        buttonReset.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.registerButton:
                    registerUser();
                    break;

                case R.id.resetButton:
                    resetValues();
                    break;
            }
    }

    private void resetValues(){
        editTextName.setText("");
        editTextUsername.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextAge.setText("");
    }

    private void registerUser(){
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String age = editTextAge.getText().toString().trim();

            if (name.isEmpty()) {
                editTextName.setError("Please enter name");
                editTextName.requestFocus();
                return;
            }

            if (username.isEmpty()) {
                editTextUsername.setError("Please enter username");
                editTextUsername.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                editTextEmail.setError("Email can't be empty");
                editTextEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Invalid email address");
                editTextEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError("Password can't be empty");
                editTextPassword.requestFocus();
                return;
            }

            if (password.length() < 8) {
                editTextPassword.setError("Password should be more than 8 characters long");
                editTextPassword.requestFocus();
                return;
            }

            if (age.isEmpty()) {
                editTextAge.setError("Age can't be empty");
                editTextAge.requestFocus();
                return;
            }

        progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(name, username, email, password, age);

                                FirebaseDatabase.getInstance().getReferenceFromUrl("https://authapp-8176f-default-rtdb.firebaseio.com/")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressBar.setVisibility(View.GONE);

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterUser.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(RegisterUser.this, "User Registration failed! Try Again", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            } else {
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(RegisterUser.this, "User Registration failed! Try Again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });




    }
}