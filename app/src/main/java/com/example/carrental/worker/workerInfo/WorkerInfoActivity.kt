package com.example.carrental.worker.workerInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrental.R
import com.example.carrental.login.MyDatabaseHelper
import com.example.carrental.worker.customer.Customer

import kotlinx.android.synthetic.main.activity_worker_info.*

class WorkerInfoActivity : AppCompatActivity() {
    private val data=ArrayList<Worker>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_info)
        initData()
        val layoutManager= LinearLayoutManager(this)
        recyclerview_worker_info.layoutManager=layoutManager

        val adapter= WokerAdapter(data)
        recyclerview_worker_info.adapter=adapter
    }

    private fun initData() {
        val dbHelper= MyDatabaseHelper(this,"CustomerStore.db",null,1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("worker",null,null,null,null,null,null)
        while(cursor.moveToNext()){
            val id=cursor.getInt(cursor.getColumnIndex("id"))
            val pwd=cursor.getString(cursor.getColumnIndex("password"))
            val name=cursor.getString(cursor.getColumnIndex("name"))
            val phone=cursor.getString(cursor.getColumnIndex("phone"))
            val age=cursor.getInt(cursor.getColumnIndex("age"))

            data.add(Worker(id,pwd,name,phone,age))
        }
        cursor.close()
    }
}
