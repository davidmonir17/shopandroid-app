package com.example.tonytech.david_project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatequantaty extends AppCompatActivity {
    shoopingdb markit;
Cursor cursor;
int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markit=new shoopingdb(this);
        setContentView(R.layout.activity_updatequantaty);
      final  EditText cou= (EditText)findViewById(R.id.editText14);

        Button btn=(Button)findViewById(R.id.button9);
        cursor=markit.getcat(getIntent().getExtras().getInt("iddd"));
        num=cursor.getInt(3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int y=Integer.parseInt(cou.getText().toString());
               if(y>num)
               {
                   Toast.makeText(getApplicationContext(),"is over quantity we have!",Toast.LENGTH_LONG).show();

               }else {
                   markit.updateda(getIntent().getExtras().getInt("iddd"),y);
                   Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();


               }
            }
        });
    }
}
