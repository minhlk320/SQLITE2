package com.minh.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_exit;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuexample,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.thongtinsach:
                Intent intent_book = new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent_book);
                return true;
            case R.id.thongtintacgia:
                Intent intent_author = new Intent(MainActivity.this,AuthorActivity.class);
                startActivity(intent_author);
                return true;
            case R.id.timkiem:
                Intent intent_search = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent_search);
                return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_exit = findViewById(R.id.btn_exit);
        button_exit.setOnCreateContextMenuListener(this);

    }
}
