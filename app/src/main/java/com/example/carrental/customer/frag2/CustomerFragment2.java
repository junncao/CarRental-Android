package com.example.carrental.customer.frag2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carrental.R;
import com.example.carrental.login.MyDatabaseHelper;

public class CustomerFragment2 extends Fragment {
    MyDatabaseHelper dbHelper;
    SQLiteDatabase db;
    String myID;
    public CustomerFragment2(String myID) {
        this.myID=myID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.cus_fragment2,container,false);

        dbHelper = new MyDatabaseHelper(getContext(), "CustomerStore.db", null,1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.query(
                "customer",
                null,
                "id="+myID,
                null,
                null,
                null,
                null,
                null

        );
        cursor.moveToNext();
        String name=cursor.getString(cursor.getColumnIndex("name"));
        int isVip=cursor.getInt(cursor.getColumnIndex("isVip"));
        String phone=cursor.getString(cursor.getColumnIndex("phone"));
        String credit=cursor.getString(cursor.getColumnIndex("credit"));
        TextView id_textview=view.findViewById(R.id.id_textview);
        TextView name_textview=view.findViewById(R.id.name_textview);
        TextView isvip_textview=view.findViewById(R.id.isvip_textview);
        TextView phone_textview=view.findViewById(R.id.phone_textview);
        TextView credit_textview=view.findViewById(R.id.credit_textview);
        id_textview.setText(myID);
        if(isVip==0){isvip_textview.setText("否");}
            else{isvip_textview.setText("是");}
        name_textview.setText(name);
        phone_textview.setText(phone);
        credit_textview.setText(credit);

        return view;
    }
}
