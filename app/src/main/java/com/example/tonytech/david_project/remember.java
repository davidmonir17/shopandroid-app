package com.example.tonytech.david_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class remember extends AppCompatActivity {
    shoopingdb market;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);
        final EditText b=(EditText)findViewById(R.id.editText7);
        final EditText u=(EditText)findViewById(R.id.editText11);
        final EditText n=(EditText)findViewById(R.id.editText12);
market=new shoopingdb(this);
        Button btn=(Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sr=market.chaekjob(b.getText().toString(),u.getText().toString());
                if(sr==true)
                {
                    market.updateCustomer(u.getText().toString(),n.getText().toString());
                    Toast.makeText(getApplicationContext(),"changed the password",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"the account not exists",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
