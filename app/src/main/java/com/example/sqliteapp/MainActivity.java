package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageButton buttonAdd;
    DatabaseHelper databaseHelper;
    ArrayList<Map<String,String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        buttonAdd =findViewById(R.id.button_add);
        databaseHelper=new DatabaseHelper(this);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,
                        InputActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        arrayList=databaseHelper.getAllStudents();
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,
                android.R.layout.simple_list_item_2,new String[]{"nama","alamat"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
}