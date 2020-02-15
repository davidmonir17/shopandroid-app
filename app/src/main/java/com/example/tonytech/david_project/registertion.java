package com.example.tonytech.david_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registertion extends AppCompatActivity {
    String g;
    String bd1="17/9/1997";
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registertion);
        reference= FirebaseDatabase.getInstance().getReference("skypee-73e4a").child("user");
       // getSupportActionBar().hide();
        final EditText u = (EditText) findViewById(R.id.editText3);
        final EditText p = (EditText) findViewById(R.id.editText4);
        final EditText p1 = (EditText) findViewById(R.id.editText5);
        final EditText n = (EditText) findViewById(R.id.editText6);
        //final EditText g = (EditText) findViewById(R.id.editText7);
       final RadioButton mal=(RadioButton)findViewById(R.id.radioButton);
        final RadioButton fe=(RadioButton)findViewById(R.id.radioButton2);
      //  final EditText bd = (EditText) findViewById(R.id.editText8);
        CalendarView cv=(CalendarView)findViewById(R.id.calendarView);
        final EditText j = (EditText) findViewById(R.id.editText9);
        Button done=(Button)findViewById(R.id.button3);
cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
    @Override
    public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
        bd1=dayOfMonth+"/"+month+"/"+year;
    }
});
        final shoopingdb market=new shoopingdb(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((u.getText().toString().equals("") && p.getText().toString().equals("") && p1.getText().toString().equals("") && n.getText().toString().equals("")&& j.getText().toString().equals(""))&&(!mal.isChecked()||!fe.isChecked()))
                {
                    Toast.makeText(getApplicationContext(),"please fill the requirement ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if((p.getText().toString()).equals(p1.getText().toString()))
                    {
                        boolean ch=market.cheackus(u.getText().toString());
                        if(ch==true)
                        {
                            if(mal.isChecked()){
                                g="male";
                            }
                            else {g="female";}
                            boolean i = market.addcus(n.getText().toString(), u.getText().toString(), p.getText().toString(), g, bd1, j.getText().toString());
                           // client client=new client(n.getText().toString(), u.getText().toString(), p.getText().toString(), g, bd1, j.getText().toString());
                           // reference.push().setValue(client);
                            if (i == true)
                            {
                                Toast.makeText(getApplicationContext(), "successful added ", Toast.LENGTH_LONG).show();
                            }
                            else
                                {
                                    Toast.makeText(getApplicationContext(), "Error try again :(", Toast.LENGTH_LONG).show();

                                }

                        }
                        else
                            {
                                Toast.makeText(getApplicationContext(), "The User Is Already Exists :(   ", Toast.LENGTH_LONG).show();
                            }
                       // Toast.makeText(getApplicationContext(),"incorrect password ",Toast.LENGTH_LONG).show();



                    }else{
                        Toast.makeText(getApplicationContext(), "Password Not Matched", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
