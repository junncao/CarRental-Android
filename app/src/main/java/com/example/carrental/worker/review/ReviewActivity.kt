package com.example.carrental.worker.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrental.R
import com.example.carrental.login.MyDatabaseHelper
import com.example.carrental.worker.workerInfo.WokerAdapter
import com.example.carrental.worker.workerInfo.Worker
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.activity_worker_info.*

class ReviewActivity : AppCompatActivity() {
    private val data= mutableListOf<Review>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myID=intent.getStringExtra("id")
        setContentView(R.layout.activity_review)
        initData()
        val layoutManager= LinearLayoutManager(this)
        recyclerview_review.layoutManager=layoutManager
        val adapter= ReviewAdapter(data,myID)
        recyclerview_review.adapter=adapter
    }

    private fun initData() {
        val dbHelper= MyDatabaseHelper(this,"CustomerStore.db",null,1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("rental",null,"state=1",null,null,null,null)
        while(cursor.moveToNext()){
            val id=cursor.getInt(cursor.getColumnIndex("id"))
            val customerID=cursor.getInt(cursor.getColumnIndex("customerID"))
            val carID=cursor.getInt(cursor.getColumnIndex("carID"))
            val datetime=cursor.getString(cursor.getColumnIndex("datetime"))

            data.add(Review(id,customerID,carID,datetime))
        }
        cursor.close()
    }
}
