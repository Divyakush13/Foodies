package com.example.myracipy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipyDetails extends AppCompatActivity {
    int id;
    TextView textview_meal_name,text_meal_source;
    ImageView image_meal_image;
    RecyclerView recycler_meal_ingrident;
    Requestmanager manager;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipy_details);

        finfview();
        
        id =Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new Requestmanager(this);
//        manager.getRandomRecipy();
        

    }

    private void finfview() {
        textview_meal_name = findViewById(R.id.textview_meal_name);
        text_meal_source = findViewById(R.id.text_meal_source);
        image_meal_image = findViewById(R.id.image_meal_image);
        recycler_meal_ingrident = findViewById(R.id.recycler_meal_ingrident);
    }
}