package com.example.carrental.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private String createCustomer="create table customer("+
            "id integer primary key autoincrement,"+
            "password text,"+
            "name text,"+
            "isVip integer,"+
            "phone text,"+
            "credit integer)";
    private String createWorker="create table worker("+
            "id integer primary key autoincrement,"+
            "password text,"+
            "name text,"+
            "phone text,"+
            "age integer)";
    private String createCar="create table car("+
            "id integer primary key autoincrement,"+
            "license text,"+
            "brand text,"+
            "state integer,"+//1--5,the lower,the better
            "valid integer,"+
            "rent integer,"+//租金
            "deposit integer)";//押金
    private String createRental="create table rental("+
            "id integer primary key autoincrement,"+
            "customerID integer,"+
            "state integer,"+
            "carID integer,"+
            "datetime text)";
    private String createFinish= "create table finish("+
            "id integer primary key autoincrement,"+
            "customerID integer,"+
            "carID integer,"+
            "wokerID integer,"+
            "updateTime text)";
    private String createIncome="create table income("+
            "id integer primary key autoincrement,"+
            "money integer,"+
            "customerID integer,"+
            "updateTime text)";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db!=null){
            db.execSQL(createCustomer);
            db.execSQL(createCar);
            db.execSQL(createRental);
            db.execSQL(createFinish);
            db.execSQL(createWorker);
            db.execSQL(createIncome);
            Toast.makeText(mContext,"succeed!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
