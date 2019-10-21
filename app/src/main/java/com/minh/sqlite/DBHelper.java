package com.minh.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"BookDB.sqlite", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AUTHOR(ID_AUTHOR INT PRIMARY KEY, NAME TEXT,ADDRESS TEXT, EMAIL TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS BOOK(ID_BOOK INT PRIMARY KEY, TITLE TEXT, ID_AUTHOR INT " +
                "CONSTRAINT ID_AUTHOR REFERENCES AUTHOR(ID_AUTHOR) ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BOOK");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AUTHOR");
        onCreate(sqLiteDatabase);
    }
    public int insertAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_AUTHOR", author.getId_author());
        contentValues.put("NAME", author.getName());
        contentValues.put("ADDRESS", author.getAddress());
        contentValues.put("EMAIL", author.getEmail());
        int result = (int)db.insert("AUTHOR", null, contentValues);;
        return result;
    }
    public int insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_BOOK", book.getId());
        contentValues.put("TITLE", book.getTitle());
        contentValues.put("ID_AUTHOR", book.getAuthor());
        int result = (int)db.insert("BOOK", null, contentValues);;

        return result;
    }
    public Book getBookByID(int id){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BOOK WHERE ID_BOOK = " + id,null);
        if(cursor!=null)
            cursor.moveToFirst();
        Book book = new Book(cursor.getInt(0),cursor.getString(1), cursor.getInt(2));
        cursor.close();
        db.close();

        return  book;
    }
    public Author getAuthorByID(int id){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM AUTHOR WHERE ID_AUTHOR = " + id,null);
        if(cursor!=null)
            cursor.moveToFirst();
        if(cursor.isAfterLast())
            return null;
        Author author = new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        cursor.close();
        db.close();

        return  author;
    }
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> list = new ArrayList<Book>();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BOOK",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new Book(cursor.getInt(0),cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return  list;
    }
    public ArrayList<Author> getAllAuthors(){
        ArrayList<Author> list = new ArrayList<Author>();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM AUTHOR",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new Author(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return  list;
    }
    public ArrayList<String> getBookAuthor(int id){
        ArrayList<String> arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT BOOKS.ID_AUTHOR, TITILE FROM AUTHOR INNER JOIN BOOK ON AUTHOR.ID_AUTHOR = BOOK.ID_AUTHOR" +
                "   WHERE AUHTOR.ID_AUTHOR="+id;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getInt(0)+"");
        }
        //Todo
        return null;
    }
    public boolean DeleteBook(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if(sqLiteDatabase.delete("BOOK","ID_BOOK"+"=?", new String[]{String.valueOf(id)})>0){
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }
    public boolean updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", book.getTitle());
        contentValues.put("ID_AUTHOR", book.getAuthor());
        if(sqLiteDatabase.update("BOOK", contentValues,"ID_BOOK" + "=?",new String[]{String.valueOf(book.getId())})>0){
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }
}
