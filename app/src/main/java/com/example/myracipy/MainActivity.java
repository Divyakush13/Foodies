package com.example.myracipy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myracipy.Adapter.RandomRecipyAdapter;
import com.example.myracipy.Listner.RacipyClickListner;
import com.example.myracipy.Listner.RandonRecipyResponseLintner;
import com.example.myracipy.Modals.RandomRecipyapiresponse;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    Requestmanager manager;
    RandomRecipyAdapter randomRecipyAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        searchView = findViewById(R.id.searchview_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipy(randonRecipyResponseLintner,tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                 R.array.tags,
                 R.layout.spinner_text
        );

        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new Requestmanager(this);

        // manager.getRandomRecipy(randonRecipyResponseLintner);
       //  dialog.show();
    }

    private final RandonRecipyResponseLintner randonRecipyResponseLintner = new RandonRecipyResponseLintner() {
        @Override
        public void didFetch(RandomRecipyapiresponse response, String message) {
             dialog.dismiss();
             recyclerView = findViewById(R.id.recycl_Random);
             recyclerView.setHasFixedSize(true);
             recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
             randomRecipyAdapter = new RandomRecipyAdapter(MainActivity.this,response.recipes,recipeClickListner);
             recyclerView.setAdapter(randomRecipyAdapter);
        }

        @Override
        public void didErropr(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipy(randonRecipyResponseLintner,tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


private  final RacipyClickListner recipeClickListner = new RacipyClickListner() {
    @Override
    public void onRecipyclicked(String id) {
       startActivity(new Intent(MainActivity.this,RecipyDetails.class)
        .putExtra("id",id));
    }
};

}