package com.example.tonytech.david_project;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class cartact extends AppCompatActivity {
    TextView price;
    Button or_btn;
    shoopingdb markit;
    ArrayAdapter<String> adapter;
    ListView lis;
    int num;
    int idorder;
    Cursor cursor;
    Cursor iduser;
    DatabaseReference reference;
    int totalpric=0;
    int pdssd=0;
    String ididididididi="";
    //proid,proname,price,quantity,catsid,daid,custoid,prductid,quantaty
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartact);
        reference= FirebaseDatabase.getInstance().getReference("skypee-73e4a").child("selled");
        glob g= (glob)getApplicationContext();
        String username=g.getUser();
        markit=new shoopingdb(this);
        iduser=markit.getcusid(username);
        final EditText loc=(EditText)findViewById(R.id.editText13) ;
        price=(TextView)findViewById(R.id.textView17);
        or_btn=(Button)findViewById(R.id.button8);

       lis=(ListView)findViewById(R.id.cartli);
       registerForContextMenu(lis);
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        lis.setAdapter(adapter);
        cursor=markit.getcart(iduser.getInt(0));
        while (!cursor.isAfterLast())
        {
            adapter.add(cursor.getString(1));
            totalpric=totalpric+(cursor.getInt(2)*cursor.getInt(8));
            cursor.moveToNext();
        }
        price.setText("$"+String.valueOf(totalpric));
        or_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            //proid,proname,price,quantity,catsid,daid,custoid,prductid,quantaty


            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                String currentdate=DateFormat.getDateInstance().format(calendar.getTime());
                markit.makeorder(currentdate,iduser.getInt(0),loc.getText().toString());
                idorder=markit.lastorder();
               Cursor c=markit.getcart(iduser.getInt(0));

                while (!c.isAfterLast())
                {
                 markit.makeorderdetails(idorder,c.getInt(0),c.getInt(8));
                 markit.updateproduct(c.getInt(0),c.getInt(3)-c.getInt(8));
                // getus(c.getString(1));
                 /*if(ididididididi!="") {
                     DatabaseReference r= FirebaseDatabase.getInstance().getReference("skypee-73e4a").child("selled").child(ididididididi);
                     r.removeValue();
                 }*/

                 weborder w=new weborder(c.getString(1),c.getInt(8));
                 reference.push().setValue(w);

                 c.moveToNext();
                }
                markit.delda(iduser.getInt(0));
                Toast.makeText(getApplicationContext(),"done will sent soon",Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
      inflater.inflate(R.menu.menucont,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        String selectedshop=((TextView)info.targetView).getText().toString();
        int id=item.getItemId();
        if(id==R.id.iddel)
        {
         adapter.remove(selectedshop);
         Cursor x= markit.getcartid(selectedshop);
         totalpric=totalpric-(x.getInt(2)*x.getInt(8));
         markit.deleteda(x.getInt(7));
            price.setText("$"+String.valueOf(totalpric));
         return true;
        }
        if(id==R.id.idupd)
        {
            Cursor x= markit.getcartid(selectedshop);
            Intent i=new Intent(cartact.this,updatequantaty.class);
            i.putExtra("iddd",x.getInt(7));
            startActivity(i);
        }
        return false;
    }

    @Override
    protected void onRestart() {
        adapter.clear();
        totalpric=0;
       Cursor m =markit.getcart(iduser.getInt(0));
        while (!m.isAfterLast())
        {
            adapter.add(m.getString(1));
            totalpric=totalpric+(m.getInt(2)*m.getInt(8));
            m.moveToNext();
        }
        price.setText("$"+String.valueOf(totalpric));
        super.onRestart();
    }

/*
    public  void getus(final String x)
    {
        FirebaseDatabase.getInstance().getReference("skypee-73e4a").child("selled").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String ukk1=snapshot.getKey();
                    String u1=snapshot.child("name").getValue(String.class);
                    int yarb=snapshot.child("count").getValue(int.class);
                    if(u1.equals(x))
                    {
                        pdssd=yarb;
                        ididididididi=ukk1;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }*/
}
