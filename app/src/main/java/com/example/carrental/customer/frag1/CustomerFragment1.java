package com.example.carrental.customer.frag1;


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

public class CustomerFragment1 extends Fragment {
    MyDatabaseHelper dbHelper;
    SQLiteDatabase db;
    String myID;
    List<RentalCarModel> carData;

    public CustomerFragment1(String myID) {
        this.myID=myID;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.cus_fragment1,container,false);

        dbHelper = new MyDatabaseHelper(getContext(), "CustomerStore.db", null,1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.query(
                "car",
                new String[]{"id","license","brand","rent","deposit"},
                "valid=1 and state<5",
                null,
                null,
                null,
                null,
                null
        );
        this.carData=new ArrayList<>();
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String license=cursor.getString(cursor.getColumnIndex("license"));
            String brand=cursor.getString(cursor.getColumnIndex("brand"));
            String rent=cursor.getString(cursor.getColumnIndex("rent"));
            String deposit= cursor.getString(cursor.getColumnIndex("deposit"));
            carData.add(new RentalCarModel(id,license,brand,rent,deposit));
        }
        cursor.close();

        RecyclerView recyclerView=view.findViewById(R.id.recyclerview_rental_car);
        RentalCarAdapter adapter=new RentalCarAdapter(carData,myID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);


        return view;
    }
}
