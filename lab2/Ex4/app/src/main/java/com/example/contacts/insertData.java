package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contacts.data.saveData;
import com.example.contacts.database.DBHandler;

import java.util.List;

public class insertData extends AppCompatActivity {
    Button saveBtn;
    String name,number,description;
    EditText nameTxt,numberTxt,desTxt;
    saveData save;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        saveBtn=findViewById(R.id.saveContactBtn);
        nameTxt=findViewById(R.id.name);
        numberTxt=findViewById(R.id.number);
        desTxt=findViewById(R.id.description);
        dbHandler=new DBHandler(this);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=nameTxt.getText().toString();
                number=numberTxt.getText().toString();
                description=desTxt.getText().toString();
                saveData(name,number,description);
            }
        });
    }
    boolean checkNumber(String num){
        List<saveData> list=dbHandler.readContact();
        for (saveData data:list) {
            if(data.getNumber().equals(num)) {
                return true;
            }
        }
        return false;
    }
    void saveData(String name,String num,String desc){
        if (name.isEmpty()){
            nameTxt.setError("Please fill it");
        }else if(number.isEmpty()){
            numberTxt.setError("Please fill it");
        }else {
            if(checkNumber(num)){
               numberTxt.setError("Number already exists");
            }else {
                save = new saveData(name, num, desc);
                dbHandler.insertContact(save);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }


    }
}