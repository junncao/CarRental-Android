package com.example.carrental.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carrental.R;
import com.example.carrental.customer.CustomerActivity;
import com.example.carrental.worker.WorkerActivity;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private EditText editIdView;
    private EditText editPwdView;
    private String tableName;
    private int identity=0;//0:not choose  1:customer   2:worker
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "CustomerStore.db", null,1);
        db = dbHelper.getWritableDatabase();
        editIdView=findViewById(R.id.edit_id);
        editPwdView=findViewById(R.id.edit_password);
    }


    public void onRadioBtnClicked(View view) {
        switch (view.getId()){
            case R.id.radioCustomer:
                identity=1;
                tableName="customer";
                break;

            case R.id.radioWorker:
                identity=2;
                tableName="worker";
                break;
        }

    }

    public void onLoginBtnClicked(View view) {
        if(identity==0){
            Toast.makeText(this,"请选择您的身份",Toast.LENGTH_SHORT).show();
        }else if(editIdView.getText().toString().length()==0){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
        }else if(editPwdView.getText().toString().length()==0){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else{
            Cursor cursor=db.query(
                    tableName,
                    new String[]{"password"},
                    "id=? and password=?",
                    new String[]{editIdView.getText().toString(),editPwdView.getText().toString()},
                    null,
                    null,
                    null,
                    null
            );
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                if(tableName.equals("customer")){
                    Intent intent=new Intent(MainActivity.this, CustomerActivity.class);
                    intent.putExtra("id",editIdView.getText().toString());
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(MainActivity.this, WorkerActivity.class);
                    intent.putExtra("id",editIdView.getText().toString());
                    startActivity(intent);
                }

            } else {
                Toast.makeText(this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void onLogoutBtnClicked(View view) {
        finish();
    }
}
