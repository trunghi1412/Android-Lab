package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.view.View.OnClickListener;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button call =(Button) findViewById(R.id.call);
        final EditText number =(EditText) findViewById(R.id.number);

        call.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent =new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +number.getText()));
                callIntent.setFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                startActivity(callIntent);
            }
        });
    }



}