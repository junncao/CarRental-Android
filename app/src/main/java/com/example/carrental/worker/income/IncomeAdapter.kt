package com.example.carrental.worker.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.R

data class Income(val id:Int,val money:Int,val customerID:Int,val updateTime:String)
class IncomeAdapter(val listdata:List<Income>): RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id:TextView=view.findViewById(R.id.id_income)
        val money:TextView=view.findViewById(R.id.money_income)
        val customerID:TextView=view.findViewById(R.id.customerID_income)
        val updateTime:TextView=view.findViewById(R.id.updateTime_income)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.income_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val woker=listdata[position]
        holder.id.setText(woker.id.toString())
        holder.money.setText(woker.money.toString())
        holder.customerID.setText(woker.customerID.toString())
        holder.updateTime.setText(woker.updateTime)
    }
}