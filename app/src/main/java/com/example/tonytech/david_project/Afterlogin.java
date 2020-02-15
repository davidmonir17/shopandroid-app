package com.example.tonytech.david_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Afterlogin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    shoopingdb market;
    ArrayAdapter<String> adapter;
    ListView list;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        ////////////////////////////////////////////////////////////////
     //   TextView change=(TextView)findViewById(R.id.nameuser);
       // change.setText(getIntent().getExtras().getString("name"));
        market=new shoopingdb(this);
        list=(ListView)findViewById(R.id.mainli);
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        cursor=market.Fetchall();
        while (!cursor.isAfterLast())
        {
            adapter.add(cursor.getString(1));
            cursor.moveToNext();
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Afterlogin.this,detail.class);
                cursor.moveToPosition(position);
                i.putExtra("employeeID",cursor.getInt(0));
                startActivity(i);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.afterlogin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_voice) {
            list.setVisibility(View.INVISIBLE);
            voiceFragment voiceFragment= new voiceFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.lay,voiceFragment).commit();
            // Handle the camera action
        } else if (id == R.id.search_text) {
            list.setVisibility(View.INVISIBLE);

            searchFragment searchFragment=new searchFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.lay,searchFragment).commit();
        } else if (id == R.id.nav_barcode) {

            Intent n=new Intent(Afterlogin.this,barcode.class);
            startActivity(n);

        } else if(id == R.id.cart){

            Intent n=new Intent(Afterlogin.this,cartact.class);
            startActivity(n);

        }
        else if (id == R.id.Cat_Clothes) {
            list.setVisibility(View.INVISIBLE);

            clothesFragment clothesFragment=new clothesFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.lay,clothesFragment).commit();

        } else if (id == R.id.Cat_Mob) {
            list.setVisibility(View.INVISIBLE);

            mobileFragment mobileFragment= new mobileFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.lay,mobileFragment).commit();

        } else if (id == R.id.Cat_shoes) {
            list.setVisibility(View.INVISIBLE);

            shoesFragment shoesFragment= new shoesFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.lay,shoesFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
