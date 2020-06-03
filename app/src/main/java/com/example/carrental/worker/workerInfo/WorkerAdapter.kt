package com.example.carrental.worker.workerInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.R

data class Worker(val id:Int,val pwd:String,val name:String,val phone:String,val age:Int)
class WokerAdapter(val listdata:List<Worker>): RecyclerView.Adapter<WokerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id:TextView=view.findViewById(R.id.id_woker)
        val pwd:TextView=view.findViewById(R.id.pwd_woker)
        val name:TextView=view.findViewById(R.id.name_woker)
        val phone:TextView=view.findViewById(R.id.phone_woker)
        val age:TextView=view.findViewById(R.id.age_woker)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.woker_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val woker=listdata[position]
        holder.id.setText(woker.id.toString())
        holder.pwd.setText(woker.pwd)
        holder.name.setText(woker.name)
        holder.phone.setText(woker.phone)
        holder.age.setText(woker.age.toString())
    }
}