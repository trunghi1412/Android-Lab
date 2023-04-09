package com.example.lab2_bt5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSend = (Button)findViewById(R.id.sendMail);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(Intent.ACTION_SEND);
                si.setType("message/rfc822");
                si.putExtra(Intent.EXTRA_EMAIL, new String[]{"19521511@gm.uit.edu.vn"});
                si.putExtra(Intent.EXTRA_SUBJECT, "Hi Hieu");
                si.putExtra(Intent.EXTRA_TEXT, "Pham Trung Hieu - 19521511 - Student UIT");
                startActivity(Intent.createChooser(si,"Choose Mail App"));
            }
        });
    }
}