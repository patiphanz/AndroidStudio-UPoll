package com.patiphan.upoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPollPage extends AppCompatActivity {

    private Button postBtn;
    private TextView pollText;

    private Firebase mRootRef;
    private DatabaseReference mdatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poll_page);

        Firebase.setAndroidContext(this);

        pollText = (TextView) findViewById(R.id.pollText);
        postBtn = (Button) findViewById(R.id.postBtn);

        // Initialize Firebase
        mdatabaseRef = FirebaseDatabase.getInstance().getReference();
        mRootRef = new Firebase("https://upoll-app-c8dbb.firebaseio.com/").child("User_Details").push();

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mName = pollText.getText().toString().trim();
                if(mName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter something", Toast.LENGTH_SHORT).show();
                    return;
                }
                Firebase childRef_name = mRootRef.child("Poll_Title");
                childRef_name.setValue(mName);
                Toast.makeText(getApplicationContext(),"Poll created", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(NewPollPage.this, PollPage.class);
                finish();
                startActivity(i);
            }
        });

    }
}
