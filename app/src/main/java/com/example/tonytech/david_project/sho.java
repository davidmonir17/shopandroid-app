package com.example.tonytech.david_project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class sho extends AppCompatActivity {
    ListView productList;
    ArrayAdapter<String> adapter;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sho);

        final EditText n1 =(EditText)findViewById(R.id.editText10);
        Button soo=(Button)findViewById(R.id.button4);
        productList=(ListView) findViewById(R.id.li);
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        productList.setAdapter(adapter);
        final shoopingdb dbd=new shoopingdb(this);
        soo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor= dbd.searchcat(n1.getText().toString());
                while (!cursor.isAfterLast())
                {
                    adapter.add(cursor.getString(1));
                    cursor.moveToNext();
                }
            }
        });

    }
}
