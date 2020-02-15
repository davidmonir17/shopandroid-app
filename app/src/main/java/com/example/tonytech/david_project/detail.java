package com.example.tonytech.david_project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class detail extends AppCompatActivity {
    TextView count;
    TextView price;
    ImageButton mi_btn;
    ImageButton add_btn;
    Button bu_btn;
    shoopingdb markit;
    int num;
    Cursor cursor;
    Cursor iduser;
    int totalpric=0;
    @Override
    //proid,proname,price,quantity,catsid,daid,custoid,prductid,quantaty

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        glob g= (glob)getApplicationContext();
        String username=g.getUser();
        markit=new shoopingdb(this);
        iduser=markit.getcusid(username);
        add_btn=(ImageButton)findViewById(R.id.add);
        mi_btn=(ImageButton)findViewById(R.id.minus);
        bu_btn=(Button)findViewById(R.id.button6);
        count=(TextView) findViewById(R.id.textView15);
        price=(TextView) findViewById(R.id.textView16);
        markit=new shoopingdb(this);
        cursor=markit.getcat(getIntent().getExtras().getInt("employeeID"));

        num=Integer.parseInt(count.getText().toString());
        totalpric=totalpric+(cursor.getInt(2)*num);
        price.setText("$"+String.valueOf(totalpric));
        mi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==1)
                {
                    Toast.makeText(getApplicationContext(),"is at least num",Toast.LENGTH_LONG).show();
                }else {
                    num=num-1;
                    count.setText(String.valueOf(num));

                }
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=cursor.getInt(3);
                if(num==x){
                    Toast.makeText(getApplicationContext(),"is all quantaty we have!",Toast.LENGTH_LONG).show();
                }else
                {
                    num=num+1;
                    count.setText(String.valueOf(num));
                }
            }
        });
        bu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ch=markit.cheackproduct(getIntent().getExtras().getInt("employeeID"));
                if(ch)
                {
                    markit.updateda(getIntent().getExtras().getInt("employeeID"),num);
                }
                else {
                    markit.insertdafkra(iduser.getInt(0), cursor.getInt(0), num);
                }
                Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_LONG).show();

            }
        });
    }
}
