package com.example.carrental.worker.review

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.R
import com.example.carrental.login.MyDatabaseHelper
import java.text.SimpleDateFormat
import java.util.*

data class Review(val id:Int,val customerID:Int,val carID:Int,val datetime:String)
class ReviewAdapter(val listdata:MutableList<Review>,val myID:String): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    lateinit var mContext:Context
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id:TextView=view.findViewById(R.id.id_review)
        val cusID:TextView=view.findViewById(R.id.customer_id_review)
        val carID:TextView=view.findViewById(R.id.car_id_review)
        val datetime_review:TextView=view.findViewById(R.id.datetime_review)
        val btn:Button=view.findViewById(R.id.btn_review_pass)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext=parent.context
        val view=LayoutInflater.from(parent.context).inflate(R.layout.review_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review=listdata[position]
        holder.id.setText(review.id.toString())
        holder.cusID.setText(review.customerID.toString())
        holder.carID.setText(review.carID.toString())
        holder.datetime_review.setText(review.datetime)
        holder.btn.setOnClickListener {
            val builder=AlertDialog.Builder(mContext)
            builder.setTitle("提示")
            builder.setMessage("确定审核通过吗？")
            builder.setPositiveButton("确认"){dialog,which->
                val dbHelper= MyDatabaseHelper(mContext,"CustomerStore.db",null,1)
                val db=dbHelper.writableDatabase
                db.delete(//删除rental中对应数据
                        "rental",
                        "id="+review.id,
                        null
                )
                val insertFinish=ContentValues()

                insertFinish.put("customerID",review.customerID)
                insertFinish.put("carID",review.carID)
                insertFinish.put("wokerID",myID)

                val dateformat=SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date=Date()

                insertFinish.put("updateTime",dateformat.format(date).toString())
                val flag_insert_finish=db.insert(//把订单插入finish中
                    "finish",
                    null,
                     insertFinish
                )

                val updateCar=ContentValues()
                updateCar.put("valid",1)
                db.update(//对应车子valid设为1
                        "car",
                        updateCar,
                        "id="+review.carID,
                        null
                )

                val cursor=db.query(//查一下车子租金
                        "car",
                        arrayOf("rent"),
                        "id="+review.carID,
                        null,
                        null,
                        null,
                        null
                )
                cursor.moveToFirst()
                val rent:Int=cursor.getInt(cursor.getColumnIndex("rent"))
                val insertIncome=ContentValues()
                insertIncome.put("customerID",review.customerID)
                insertIncome.put("updateTime",dateformat.format(date).toString())
                insertIncome.put("money",rent)
                val flag_insert_income=db.insert( //收入增加
                        "income",
                        null,
                        insertIncome
                )
                Log.d("insertIncome", flag_insert_income.toString())
                listdata.removeAt(position)
                notifyItemRemoved(position)
                // User clicked OK button.
                Toast.makeText(mContext,
                        "审核通过成功！",
                        Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("取消"){dialog,which->

            }
             builder.show()

        }
    }
}