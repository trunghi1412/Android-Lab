package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.adapter.Recycleviewadapter;
import com.example.contacts.data.saveData;
import com.example.contacts.database.DBHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Recycleviewadapter adapter;
    Button insertContact;
    EditText searchBar;
    List<saveData> list;
    DBHandler dbHandler;

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        insertContact=findViewById(R.id.insertContact);
        searchBar=findViewById(R.id.searchBar);
        dbHandler=new DBHandler(this);
        list=dbHandler.readContact();
        Collections.sort(list, new Comparator<saveData>() {
            @Override
            public int compare(saveData item1, saveData item2) {
                return  Character.compare(item1.getName().charAt(0),item2.getName().charAt(0));

            }
        });

        adapter=new Recycleviewadapter(list,this);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        insertContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),insertData.class);
                startActivity(i);
            }
        });
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String x=list.get(viewHolder.getAbsoluteAdapterPosition()).getNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + x));
                startActivity(intent);
            }
        }).attachToRecyclerView(recyclerView);

    }
    private void filter(String txt){
        ArrayList<saveData>listData=new ArrayList<>();
        for(saveData data:list){
            if (data.getName().toLowerCase().contains(txt.toLowerCase())){
                listData.add(data);
            }
        }
       adapter.filterList(listData);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.trash:
                Intent i=new Intent(getApplicationContext(),trash.class);
                startActivity(i);
                return true;
            case R.id.deleteSelected:
                if(adapter.getSelectedItems().size()==0){
                    Toast.makeText(this, "First select items", Toast.LENGTH_SHORT).show();
                }else {
                    for (String data : adapter.getSelectedItems()) {
                        dbHandler.moveToTrash(data);
                    }
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                    Intent x=new Intent(this,MainActivity.class);
                    startActivity(x);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menus,menu);
        return true;
    }

}