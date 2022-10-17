package com.example.authapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileViewUser extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;

    private Button logout;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view_user);

        progressBar=findViewById(R.id.profileViewIndeterminateProgressbar);

        logout=(Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                FirebaseAuth.getInstance().signOut();

                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(ProfileViewUser.this,MainActivity.class));
            }
        });

        reference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://authapp-8176f-default-rtdb.firebaseio.com/");
        user=FirebaseAuth.getInstance().getCurrentUser();
        userId=user.getUid();

        final TextView textview_name=findViewById(R.id.textViewNameProfile);
        final TextView textview_email=findViewById(R.id.textViewEmailProfile);
        final TextView textview_age=findViewById(R.id.textViewAgeProfile);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile=snapshot.getValue(User.class);

                if(userProfile!=null){
                    String name=userProfile.getName();
                    String email=userProfile.getEmail();
                    String age=Integer.toString(userProfile.getAge());

                    textview_name.setText(name);
                    textview_email.setText(email);
                    textview_age.setText(age);
                }
                else{
                    Toast.makeText(ProfileViewUser.this, "No data found! Try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileViewUser.this,"Something wrong happened! Try Again!",Toast.LENGTH_LONG);
            }
        });



    }


}