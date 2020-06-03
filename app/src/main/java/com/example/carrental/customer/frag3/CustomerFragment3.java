package com.example.carrental.customer.frag3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;
import com.example.carrental.login.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragment3 extends Fragment {
    MyDatabaseHelper dbHelper;
    SQLiteDatabase db;
    String myID;
    List<ReturnCarModel> returnCarData;
    public CustomerFragment3(String myID) {
        this.myID=myID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.cus_fragment3,container,false);

        dbHelper = new MyDatabaseHelper(getContext(), "CustomerStore.db", null,1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery(
                "select rental.id,license,brand from car,rental where rental.carID=car.id and rental.state=1 and  rental.customerID=?",
                new String[]{myID}
        );//查询还车了，但是在审核中的
        this.returnCarData=new ArrayList<>();
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String license=cursor.getString(cursor.getColumnIndex("license"));
            String brand=cursor.getString(cursor.getColumnIndex("brand"));
            returnCarData.add(new ReturnCarModel(id,license,brand,0));
        }
        cursor.close();

        //查询租借过的，还没还的
        cursor=db.rawQuery(
                "select rental.id,license,brand from car,rental where rental.carID=car.id and rental.state=0 and  rental.customerID=?",
                new String[]{myID}
        );

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String license=cursor.getString(cursor.getColumnIndex("license"));
            String brand=cursor.getString(cursor.getColumnIndex("brand"));
            returnCarData.add(new ReturnCarModel(id,license,brand,1));
        }
        cursor.close();


        RecyclerView recyclerView=view.findViewById(R.id.recyclerview_return_car);
        recyclerView.setHasFixedSize(true);
        ReturnCarAdapter adapter=new ReturnCarAdapter(returnCarData,myID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);


        return view;
    }
}
