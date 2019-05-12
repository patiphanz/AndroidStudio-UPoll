package com.patiphan.upoll;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FriendPage extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private TextView emailText;

    RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<ViewSingleItem, ShowDataViewHolder> mFirebaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);
        emailText = (TextView) findViewById(R.id.emailText);
        emailText.setText(user.getDisplayName());

        recyclerView = (RecyclerView) findViewById(R.id.viewFriendData);
        recyclerView.setLayoutManager(new LinearLayoutManager(FriendPage.this));

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

    public static class ShowDataViewHolder extends RecyclerView.ViewHolder {
        private final TextView user_email;

        public ShowDataViewHolder(final View itemView) {
            super(itemView);
            user_email = (TextView) itemView.findViewById(R.id.fetch_email);
        }

        private void User_Email(String email) { user_email.setText(email);}
    }
}
