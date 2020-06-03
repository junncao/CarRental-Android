package com.example.carrental.worker.customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrental.R
import com.example.carrental.login.MyDatabaseHelper
import kotlinx.android.synthetic.main.activity_customer_info.*

class CustomerInfoActivity : AppCompatActivity() {
    private val data = ArrayList<Customer>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)
        initData()
        val layoutManager=LinearLayoutManager(this)
        recyclerview_customer_info.layoutManager=layoutManager
        val adapter=CustomerAdapter(data)
        recyclerview_customer_info.adapter=adapter
    }

    private fun initData() {
        val dbHelper=MyDatabaseHelper(this,"CustomerStore.db",null,1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("customer",null,null,null,null,null,null)
        while(cursor.moveToNext()){
            val id=cursor.getInt(cursor.getColumnIndex("id"))
            val pwd=cursor.getString(cursor.getColumnIndex("password"))
            val name=cursor.getString(cursor.getColumnIndex("name"))
            val isvip=cursor.getInt(cursor.getColumnIndex("isVip"))
            val phone=cursor.getString(cursor.getColumnIndex("phone"))
            val credit=cursor.getInt(cursor.getColumnIndex("credit"))
            data.add(Customer(id,pwd,name,isvip,phone,credit))
        }
        cursor.close()
    }
}
 