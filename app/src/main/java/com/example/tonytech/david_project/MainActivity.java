package com.example.tonytech.david_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    shoopingdb market;
    View view;
    EditText u3;
    EditText p;
    CheckBox cheack;
    String lol="";
    Boolean savelogin;
    private SharedPreferences loginpref;
    private SharedPreferences.Editor loginprefeditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  view=this.getWindow().getDecorView();
       // view.setBackgroundResource(R.color.orange);
        getSupportActionBar().hide();
        Button reg= (Button)findViewById(R.id.button2);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,registertion.class);
                startActivity(i);
            }
        });
       u3=(EditText)findViewById(R.id.editText);
        p=(EditText)findViewById(R.id.editText2);
        cheack=(CheckBox)findViewById(R.id.checkBox);
        Button log= (Button)findViewById(R.id.button);
        market=new shoopingdb(this);

        loginpref=getSharedPreferences("loginPref",MODE_PRIVATE);
        loginprefeditor=loginpref.edit();
        savelogin=loginpref.getBoolean("savelogin",false);
        if(savelogin==true)
        {
            u3.setText(loginpref.getString("username",""));
            p.setText(loginpref.getString("password",""));
            cheack.setChecked(true);

        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(u3.getWindowToken(),0);
                SQLiteDatabase database=null;

                String user=u3.getText().toString();
                String pas=p.getText().toString();

                boolean i=market.cheackacc(user,pas);
                if(i==true)
                {
                    if(cheack.isChecked())
                    {
                        loginprefeditor.putBoolean("savelogin",true);
                        loginprefeditor.putString("username",user);
                        loginprefeditor.putString("password",pas);
                        loginprefeditor.commit();

                    }
                    else {
                        loginprefeditor.clear();
                        loginprefeditor.commit();


                    }
                    market.da();
                    Intent V= new Intent(MainActivity.this,Afterlogin.class);
                    glob g= (glob)getApplicationContext();
                    g.setUser(u3.getText().toString());
                    V.putExtra("name",u3.getText().toString());
                    startActivity(V);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"failed log ",Toast.LENGTH_LONG).show();

                }
            }
        });
        TextView tx= (TextView)findViewById(R.id.textView14);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,remember.class);
                startActivity(i);
            }
        });


    }
 /*   public  boolean getus()
    {
        FirebaseDatabase.getInstance().getReference("skypee-73e4a").child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String ukk1=snapshot.getKey();
                    String u1=snapshot.child("user").getValue(String.class);
                    String pdssd=snapshot.child("pass").getValue(String.class);
                    if(u1.equals(u3.getText().toString())&&pdssd.equals(p.getText().toString()))
                    {
                        lol="yes";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(lol=="yes")
        {
            return true;
        }else
            return false;
    }*/
}
