package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contacts.database.DBHandler;

public class ViewContact extends AppCompatActivity {
    Button save,updateBtn,deleteBtn,sharebtn;
    EditText name,num,des;
    String na,nu,de,id;

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        save=findViewById(R.id.saveContactBtn);
        updateBtn=findViewById(R.id.updateBtn);
        deleteBtn=findViewById(R.id.deleteBtn);
        sharebtn=findViewById(R.id.share);
        name=findViewById(R.id.name);
        num=findViewById(R.id.number);
        des=findViewById(R.id.description);
        name.setEnabled(false);
        num.setEnabled(false);
        des.setEnabled(false);
        save.setVisibility(View.INVISIBLE);
        id=getIntent().getExtras().getString("id");
        na=getIntent().getExtras().getString("name");
        nu=getIntent().getExtras().getString("number");
        de=getIntent().getExtras().getString("description");
        name.setText(na);
        num.setText(nu);
        des.setText(de);
        DBHandler dbHandler=new DBHandler(getApplicationContext());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                na=name.getText().toString();
                nu=num.getText().toString();
                de=des.getText().toString();
                if(na.isEmpty()){
                    name.setError("Please fill it");
                }else if(nu.isEmpty()){
                    num.setError("Please fill it");
                }else{
                    dbHandler.update(id,na,nu,de);
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }

            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBtn.setVisibility(View.INVISIBLE);
                deleteBtn.setVisibility(View.INVISIBLE);
                sharebtn.setVisibility(View.INVISIBLE);
                name.setEnabled(true);
                num.setEnabled(true);
                des.setEnabled(true);
                save.setVisibility(View.VISIBLE);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.moveToTrash(id);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, na+":\t\t"+nu);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, "Contact");
                startActivity(shareIntent);
            }
        });
    }
}