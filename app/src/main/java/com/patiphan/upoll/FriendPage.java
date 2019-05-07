package com.patiphan.upoll;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class FriendPage extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent i = new Intent(FriendPage.this, PollPage.class);

        switch (item.getItemId()) {

            case R.id.navigation_home:
                i = new Intent(FriendPage.this, PollPage.class);
                break;

            case R.id.navigation_ulist:
                i = new Intent(FriendPage.this, UListPage.class);
                break;

            case R.id.navigation_friend:
                i = new Intent(FriendPage.this, FriendPage.class);
                break;

        }

        return loadPage(i);
    }

    public boolean loadPage(Intent i) {
        finish();
        startActivity(i);
        return true;
    }
}
