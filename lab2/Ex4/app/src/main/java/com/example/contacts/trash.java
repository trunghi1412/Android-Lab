package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.contacts.adapter.Recycleviewadapter1;
import com.example.contacts.data.saveData;
import com.example.contacts.database.DBHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class trash extends AppCompatActivity {
    RecyclerView recyclerView;
    Recycleviewadapter1 adapter;
    DBHandler handler;
    List<saveData> list;

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);
        recyclerView=findViewById(R.id.trashRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        handler=new DBHandler(getApplicationContext());
        list=handler.readTrash();
        Collections.sort(list, new Comparator<saveData>() {
            @Override
            public int compare(saveData item1, saveData item2) {
                return  Character.compare(item1.getName().charAt(0),item2.getName().charAt(0));

            }
        });
        adapter=new Recycleviewadapter1(list,handler,this);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

    }
}