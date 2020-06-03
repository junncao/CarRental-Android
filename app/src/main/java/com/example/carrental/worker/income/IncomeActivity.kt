package com.example.carrental.worker.income

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrental.R
import com.example.carrental.login.MyDatabaseHelper
import com.example.carrental.worker.workerInfo.WokerAdapter
import com.example.carrental.worker.workerInfo.Worker
import kotlinx.android.synthetic.main.activity_income.*
import kotlinx.android.synthetic.main.activity_worker_info.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class IncomeActivity : AppCompatActivity() {

    private lateinit var data:ArrayList<Income>
    override fun onCreate(savedInstanceState: Bundle?) {
        var start_flag=0
        var syear=2020
        var smonth=6
        var sday=1
        var total=0
        var fyear=2020
        var fmonth=6
        var fday=2
        var finish_flag=0


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        btn_start.setOnClickListener{
            val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                textview_start.setText("${year}年${month+1}月${dayOfMonth}日")
                syear=year
                smonth=month
                sday=day

            start_flag=1
            if(finish_flag==1 && syear<=fyear && smonth<=fmonth && sday<=fday){

                data= ArrayList()
                total=0
                val dbHelper= MyDatabaseHelper(this,"CustomerStore.db",null,1)
                val db=dbHelper.writableDatabase
                val cursor=db.query("income",
                        null,
                        null,
                        null,
                        null,null,null)
                val temp=ArrayList<Income>()
                while(cursor.moveToNext()){
                    val id=cursor.getInt(cursor.getColumnIndex("id"))
                    val money=cursor.getInt(cursor.getColumnIndex("money"))
                    val customerID=cursor.getInt(cursor.getColumnIndex("customerID"))
                    val updateTime=cursor.getString(cursor.getColumnIndex("updateTime"))
                    temp.add(Income(id,money,customerID,updateTime))
                }
                val format=SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val s=format.parse("${syear}-${smonth+1}-${sday} 00:00:00")
                val f= format.parse("${fyear}-${fmonth+1}-${fday} 23:59:59")
                for (item in temp){
                    val n=format.parse(item.updateTime)
                    if(n.after(s) && n.before(f)){
                        data.add(item)
                        total+=item.money
                    }
                }
                total_income.setText(total.toString())
                cursor.close()


                initUI()
            }

            },year,month,day)
            dpd.show()


        }
        btn_finish.setOnClickListener{
            val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                textview_finish.setText("${year}年${month+1}月${dayOfMonth}日")
                fyear=year
                fmonth=month
                fday=day

                finish_flag=1
                if(start_flag==1 && syear<=fyear && smonth<=fmonth && sday<=fday){
                    data= ArrayList()
                    total=0
                    val dbHelper= MyDatabaseHelper(this,"CustomerStore.db",null,1)
                    val db=dbHelper.writableDatabase
                    val cursor=db.query("income",
                            null,
                            null,
                            null,
                            null,null,null)
                    val temp=ArrayList<Income>()
                    while(cursor.moveToNext()){
                        val id=cursor.getInt(cursor.getColumnIndex("id"))
                        val money=cursor.getInt(cursor.getColumnIndex("money"))
                        val customerID=cursor.getInt(cursor.getColumnIndex("customerID"))
                        val updateTime=cursor.getString(cursor.getColumnIndex("updateTime"))
                        temp.add(Income(id,money,customerID,updateTime))
                    }
                    val format=SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val s=format.parse("${syear}-${smonth+1}-${sday} 00:00:00")
                    val f= format.parse("${fyear}-${fmonth+1}-${fday} 23:59:59")
                    for (item in temp){
                        val n=format.parse(item.updateTime)
                        if(n.after(s) && n.before(f)){
                            data.add(item)
                            total+=item.money
                        }
                    }
                    cursor.close()
                    total_income.setText(total.toString())


                    initUI()
                }



            },year,month,day)



            dpd.show()

        }

    }

    private fun initUI() {
        val layoutManager= LinearLayoutManager(this)
        recyclerview_income.layoutManager=layoutManager
        val adapter= IncomeAdapter(data)
        recyclerview_income.adapter=adapter

    }


}
