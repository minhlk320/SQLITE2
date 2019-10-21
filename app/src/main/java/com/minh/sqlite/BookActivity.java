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

public class BookActivity extends AppCompatActivity {
    EditText et_id_book, et_title, et_id_author;
    Button btn_Select, btn_Delete, btn_update, btnInsert;
    GridView gridView_book;
    String tieuDe="";
    int id=0, idTacGia=0;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        connectView();
        dbHelper = new DBHelper(BookActivity.this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id_book.getText().toString().trim().equals("") || et_title.getText().toString().trim().equals("") || et_id_author.getText().toString().trim().equals(""))
                    Toast.makeText(BookActivity.this,"Không được bỏ trống!",Toast.LENGTH_LONG).show();
                else {
                    tieuDe = et_title.getText().toString();
                    idTacGia = Integer.parseInt(et_id_author.getText().toString());
                    id = Integer.parseInt(et_id_book.getText().toString());
                    int x = dbHelper.insertBook(new Book(id,tieuDe,idTacGia));
                    if(x!=0)
                        Toast.makeText(BookActivity.this,"Put item succeeded!",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(BookActivity.this,"Put item Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id_book.getText().toString().equals("")){
                    ArrayList<Book> listBook =  dbHelper.getAllBooks();
                    ArrayList<String> list = new ArrayList<>();
                    list.add("Mã Số");
                    list.add("Tiêu đề");
                    list.add("Tác giả");
                    for(Book b:listBook){
                        list.add(b.getId()+"");
                        list.add(b.getTitle());
                        list.add(b.getAuthor()+"");
                    }
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BookActivity.this,android.R.layout.simple_list_item_1,list);
                    gridView_book.setAdapter(myAdapter);
                }
                else{
                    id = Integer.parseInt(et_id_book.getText().toString());
                    Book b =  dbHelper.getBookByID(id);
                    ArrayList<String> list = new ArrayList<>();
                    list.add(b.getId()+"");
                    list.add(b.getTitle());
                    list.add(b.getAuthor()+"");
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BookActivity.this,android.R.layout.simple_list_item_1,list);
                    gridView_book.setAdapter(myAdapter);
                }
            }
        });
    }
    void connectView(){
        et_id_author = findViewById(R.id.etId_author);
        et_id_book = findViewById(R.id.etID_BOOK);
        et_title = findViewById(R.id.etTitle);
        gridView_book = findViewById(R.id.girdView_book);
        btn_Select = findViewById(R.id.btnSelect);
        btn_Delete = findViewById(R.id.btnDelete);
        btn_update = findViewById(R.id.btnUpdate);
        btnInsert = findViewById(R.id.btnSave);
    }
}
