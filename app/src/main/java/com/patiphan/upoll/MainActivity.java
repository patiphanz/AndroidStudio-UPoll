package com.patiphan.upoll;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button signin, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email2);
        password = (EditText) findViewById(R.id.password1);
        signin = (Button) findViewById(R.id.signin1);
        signup = (Button) findViewById(R.id.signup1);

        // Check if user is logged in
        if(mAuth.getCurrentUser() != null) {
            // User not login
            finish();
            startActivity(new Intent(getApplicationContext(), PollPage.class));
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = email.getText().toString();
                String getpassword = password.getText().toString();
                callsignin(getemail, getpassword);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), SignUpPage.class));
            }
        });
    }

    private void callsignin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("test", "Sign in is successful: " + task.isSuccessful());

                if(!task.isSuccessful()) {
                    Log.d("test", "Sign in with email failed: ", task.getException());
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, PollPage.class);
                    finish();
                    startActivity(i);
                }
            }
        });
    }

}
