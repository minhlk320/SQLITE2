package com.minh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText et_id_author, et_name, et_address,et_email;
    Button btn_Select, btn_Delete, btn_update, btnInsert;
    GridView gridView_author;
    String name="", address ="", email ="";
    int id=0, idTacGia=0;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        connectView();
        dbHelper = new DBHelper(AuthorActivity.this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = Integer.parseInt(et_id_author.getText().toString());
                name = et_name.getText().toString();
                address = et_address.getText().toString();
                email = et_email.getText().toString();
                if(id==0 || name.equals("")||address.equals("")||email.equals(""))
                    Toast.makeText(AuthorActivity.this,"Không được bỏ trống!",Toast.LENGTH_LONG).show();
                else {
                    int x = dbHelper.insertAuthor(new Author(id,name,address,email));
                    if(x!=0)
                        Toast.makeText(AuthorActivity.this,"Put item succeeded!",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AuthorActivity.this,"Put item Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id_author.getText().toString().equals("")){
                    ArrayList<Author> listAuthor =  dbHelper.getAllAuthors();
                    ArrayList<String> list = new ArrayList<>();
                    list.add("Mã Số");
                    list.add("Tên");
                    list.add("Địa chỉ");
                    list.add("Email");
                    for(Author b:listAuthor){
                        list.add(b.getId_author()+"");
                        list.add(b.getName());
                        list.add(b.getAddress());
                        list.add(b.getEmail());
                    }
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AuthorActivity.this,android.R.layout.simple_list_item_1,list);
                    gridView_author.setAdapter(myAdapter);
                }
                else{
                    id = Integer.parseInt(et_id_author.getText().toString());
                    Author b =  dbHelper.getAuthorByID(id);
                    ArrayList<String> list = new ArrayList<>();
                    list.add(b.getId_author()+"");
                    list.add(b.getName());
                    list.add(b.getAddress());
                    list.add(b.getEmail());
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AuthorActivity.this,android.R.layout.simple_list_item_1,list);
                    gridView_author.setAdapter(myAdapter);
                }
            }
        });
    }
    void connectView(){
        et_id_author = findViewById(R.id.etId_author);
        et_name = findViewById(R.id.etName);
        et_address = findViewById(R.id.et_address);
        et_email = findViewById(R.id.et_email);
        gridView_author = findViewById(R.id.girdView_author);
        btn_Select = findViewById(R.id.btnSelect);
        btn_Delete = findViewById(R.id.btnDelete);
        btn_update = findViewById(R.id.btnUpdate);
        btnInsert = findViewById(R.id.btnSave);
    }
}
