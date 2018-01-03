package com.kbs.practical12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Nimesh on 08-01-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //database related variables
    public static final String db_name="Mydb.db";

    public DBHelper(Context context)
    {
        super(context,db_name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("create table users (id integer primary key,name text,age integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    //
        db.execSQL("drop table if exits users");
        onCreate(db);
    }
    public boolean insertUser(String name,int age)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cvalues=new ContentValues();
        cvalues.put("name",name);
        cvalues.put("age",age);
        db.insert("users",null,cvalues);
        return true;
    }
    public boolean updateUser(int id,String name,int age){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cvalues=new ContentValues();
        cvalues.put("name",name);
        cvalues.put("age",age);
        db.update("users",cvalues,"id=?",new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteUser(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("users","id=?",new String[]{Integer.toString(id)});
    }
    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> aluser=new ArrayList();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users",null);
        c.moveToFirst();
        User tUser;
        while(c.isAfterLast()==false)
        {
            tUser=new User();
            tUser.id=c.getInt(c.getColumnIndex("id"));
            tUser.name=c.getString(c.getColumnIndex("name"));
            tUser.age=c.getInt(c.getColumnIndex("age"));
            aluser.add(tUser);
            c.moveToNext();
        }
        return  aluser;
    }
    public User getUser(int id)
    {
        User user=new User();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query("users",new String[]{"id","name","age"},"id=?",new String[]{String.valueOf(id)},null,null,null);
        c.moveToFirst();

        while(c.isAfterLast()==false)
        {

            user.id=c.getInt(c.getColumnIndex("id"));
            user.name=c.getString(c.getColumnIndex("name"));
            user.age=c.getInt(c.getColumnIndex("age"));
            c.moveToNext();
        }
        return  user;

    }
}
