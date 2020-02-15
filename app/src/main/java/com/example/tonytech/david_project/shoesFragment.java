package com.example.tonytech.david_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class shoesFragment extends Fragment {
    Cursor cursor;
    ListView mylist;
    shoopingdb market;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_shoes, container, false);
        market= new shoopingdb(getActivity());
        mylist=(ListView)root.findViewById(R.id.so);
        ArrayAdapter<String> mar=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(mar);
        cursor=market.searchcat("shoes");
        if(cursor!=null)
        {
            while (!cursor.isAfterLast())
            {
                mar.add(cursor.getString(1));
                cursor.moveToNext();
            }
            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i=new Intent(getActivity().getApplicationContext(),detail.class);
                    cursor.moveToPosition(position);
                    i.putExtra("employeeID",cursor.getInt(0));
                    startActivity(i);
                }
            });
        }
        return root;
    }


}
