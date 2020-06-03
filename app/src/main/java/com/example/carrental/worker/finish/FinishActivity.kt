package com.example.carrental.worker.finish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrental.R
import com.example.carrental.login.MyDatabaseHelper
import com.example.carrental.worker.workerInfo.WokerAdapter
import com.example.carrental.worker.workerInfo.Worker
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.activity_worker_info.*

class FinishActivity : AppCompatActivity() {
    private val data=ArrayList<Finish>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        initData()
        val layoutManager= LinearLayoutManager(this)
        recyclerview_finish.layoutManager=layoutManager

        val adapter= FinishAdapter(data)
        recyclerview_finish.adapter=adapter
    }

    private fun initData() {
        val dbHelper= MyDatabaseHelper(this,"CustomerStore.db",null,1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("finish",null,null,null,null,null,null)
        while(cursor.moveToNext()){
            val id=cursor.getInt(cursor.getColumnIndex("id"))
            val customerID=cursor.getInt(cursor.getColumnIndex("customerID"))
            val carID=cursor.getInt(cursor.getColumnIndex("carID"))
            val wokerID=cursor.getInt(cursor.getColumnIndex("wokerID"))
            val updateTime=cursor.getString(cursor.getColumnIndex("updateTime"))

            data.add(Finish(id,customerID,carID,wokerID,updateTime))
        }
        cursor.close()
    }
}
