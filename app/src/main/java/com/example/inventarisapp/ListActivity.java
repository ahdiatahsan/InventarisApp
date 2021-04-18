package com.example.inventarisapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventarisapp.DataBase.DBHelper;
import com.example.inventarisapp.DataBase.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataModel>dataModel = new ArrayList<>();
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.listviewBRG);
        db = new DBHelper(this);
        dataModel.addAll(db.getAll());
        //Menentukan bagaimana item pada RecyclerView akan tampil

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new RecyclerBarangAdapter(dataModel, getApplicationContext()));
    }


}