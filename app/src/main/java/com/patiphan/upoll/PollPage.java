package com.patiphan.upoll;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class PollPage extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_page);

        mAuth = FirebaseAuth.getInstance();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent i = new Intent(getApplicationContext(), PollPage.class);

        switch (item.getItemId()) {

            case R.id.navigation_home:
                i = new Intent(getApplicationContext(), PollPage.class);
                break;

            case R.id.navigation_ulist:
                i = new Intent(getApplicationContext(), UListPage.class);
                break;

            case R.id.navigation_friend:
                i = new Intent(getApplicationContext(), FriendPage.class);
                break;

        }

        return loadPage(i);
    }

    public boolean loadPage(Intent i) {
        startActivity(i);
        return true;
    }

    public void onCreateButtonClicked(View view) {
        Intent i = new Intent(PollPage.this, NewPollPage.class);
        startActivity(i);
    }

    public void onSignoutButtonClicked(View view) {
        mAuth.signOut();
        Intent i = new Intent(PollPage.this, MainActivity.class);
        finish();
        startActivity(i);
    }
}
