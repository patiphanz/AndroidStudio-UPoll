package com.patiphan.upoll;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UListPage extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    DatabaseReference myRef;

    private FirebaseRecyclerAdapter<ViewUList, ShowDataViewHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulist_page);
        Firebase.setAndroidContext(this);

        myRef = FirebaseDatabase.getInstance().getReference("User_Joined");
        recyclerView = (RecyclerView) findViewById(R.id.viewListData);
        recyclerView.setLayoutManager(new LinearLayoutManager(UListPage.this));

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ViewUList, ShowDataViewHolder>(ViewUList.class, R.layout.viewulist, ShowDataViewHolder.class, myRef) {
            @Override
            protected void populateViewHolder(final ShowDataViewHolder viewHolder, final ViewUList model, final int position) {
                viewHolder.List_Title(model.getList_title());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UListPage.this);
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

        Intent i = new Intent(UListPage.this, PollPage.class);

        switch (item.getItemId()) {

            case R.id.navigation_home:
                i = new Intent(UListPage.this, PollPage.class);
                break;

            case R.id.navigation_ulist:
                i = new Intent(UListPage.this, UListPage.class);
                break;

            case R.id.navigation_friend:
                i = new Intent(UListPage.this, FriendPage.class);
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
        private final TextView list_tile;

        public ShowDataViewHolder(final View itemView) {
            super(itemView);
            list_tile = (TextView) itemView.findViewById(R.id.fetch_list_title);

        }

        private void List_Title(String title) { list_tile.setText(title);}
    }
}
