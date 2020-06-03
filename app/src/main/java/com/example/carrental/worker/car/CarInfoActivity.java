package com.example.carrental.worker.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.carrental.R;
import com.example.carrental.customer.frag1.RentalCarAdapter;
import com.example.carrental.customer.frag1.RentalCarModel;
import com.example.carrental.login.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CarInfoActivity extends AppCompatActivity {
    MyDatabaseHelper dbHelper;
    SQLiteDatabase db;
    String myID;
    List<Car> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDatabaseHelper(this, "CustomerStore.db", null,1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.query(
                "car",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        data=new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String license=cursor.getString(cursor.getColumnIndex("license"));
            String brand=cursor.getString(cursor.getColumnIndex("brand"));
            int state=cursor.getInt(cursor.getColumnIndex("state"));
            int valid=cursor.getInt(cursor.getColumnIndex("valid"));
            int rent=cursor.getInt(cursor.getColumnIndex("rent"));
            int deposit= cursor.getInt(cursor.getColumnIndex("deposit"));
            data.add(new Car(id,license,brand,state,valid,rent,deposit));
        }
        cursor.close();
        setContentView(R.layout.activity_car_info);
        RecyclerView recyclerView=findViewById(R.id.car_recycleview);
        CarAdapter adapter=new CarAdapter(data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);



    }
}
