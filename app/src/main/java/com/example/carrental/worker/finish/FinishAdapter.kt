package com.example.carrental.worker.finish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.R

data class Finish(val id:Int,val customerID:Int,val carID:Int,val workerID:Int,val updateTime:String)
class FinishAdapter(val listdata:List<Finish>): RecyclerView.Adapter<FinishAdapter.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id:TextView=view.findViewById(R.id.id_finish)
        val customer_id:TextView=view.findViewById(R.id.customer_id_finish)
        val car_id:TextView=view.findViewById(R.id.car_id_finish)
        val worker_id:TextView=view.findViewById(R.id.worker_id_finish)
        val updateTime:TextView=view.findViewById(R.id.update_time_finish)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.finish_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val finish=listdata[position]
        holder.id.setText(finish.id.toString())
        holder.customer_id.setText(finish.customerID.toString())
        holder.car_id.setText(finish.carID.toString())
        holder.worker_id.setText(finish.workerID.toString())
        holder.updateTime.setText(finish.updateTime)
    }
}