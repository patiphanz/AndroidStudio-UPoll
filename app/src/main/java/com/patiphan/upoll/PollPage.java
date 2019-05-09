package com.patiphan.upoll;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PollPage extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseRecyclerAdapter<ViewSingleItem, ShowDataViewHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_page);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("User_Details");
        mAuth = FirebaseAuth.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.viewPollData);
        recyclerView.setLayoutManager(new LinearLayoutManager(PollPage.this));

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ViewSingleItem, ShowDataViewHolder>(ViewSingleItem.class, R.layout.viewsingleitem, ShowDataViewHolder.class, myRef) {
            @Override
            protected void populateViewHolder(ShowDataViewHolder viewHolder, ViewSingleItem model, final int position) {
                viewHolder.Poll_Title(model.getPoll_title());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PollPage.this);
                        builder.setMessage("Are you want to delete?").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int selectedItems = position;
                                        mFirebaseAdapter.getRef(selectedItems).removeValue();
                                        mFirebaseAdapter.notifyItemRemoved(selectedItems);
                                        recyclerView.invalidate();
                                        onStart();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.setTitle("Are you sure?");
                        dialog.show();
                    }
                });
            }
        };
        recyclerView.setAdapter(mFirebaseAdapter);
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

    public static class ShowDataViewHolder extends RecyclerView.ViewHolder {

        private final TextView poll_title;

        public ShowDataViewHolder(final View itemView) {
            super(itemView);
            poll_title = (TextView) itemView.findViewById(R.id.fetch_poll_title);
        }

        private void Poll_Title(String title) { poll_title.setText(title); }
    }
}
