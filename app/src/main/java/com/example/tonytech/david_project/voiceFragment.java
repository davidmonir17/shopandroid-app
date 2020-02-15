package com.example.tonytech.david_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class voiceFragment extends Fragment {
    EditText tex;
    Cursor cursor;
    int voicecode=1;
    ListView mylist;
    shoopingdb market;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_voice, container, false);
        market= new shoopingdb(getActivity());
        mylist=(ListView)root.findViewById(R.id.list2);
        ImageButton voi=(ImageButton)root.findViewById(R.id.imageButton);
        voi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent,voicecode);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        ArrayAdapter<String> mar=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(mar);
        cursor=market.searchcat(text.get(0));
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
    }

}
