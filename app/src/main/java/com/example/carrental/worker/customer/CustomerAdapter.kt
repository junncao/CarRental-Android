package com.example.carrental.worker.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.R
import kotlinx.android.synthetic.main.customer_item.view.*

data class Customer(val id:Int,val pwd:String,val name:String,val isVip:Int,val phone:String,val credit:Int)
class CustomerAdapter(val listdata:List<Customer>): RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id:TextView=view.findViewById(R.id.id_customer)
        val pwd:TextView=view.findViewById(R.id.pwd_customer)
        val name:TextView=view.findViewById(R.id.name_customer)
        val isvip:TextView=view.findViewById(R.id.isvip_customer)
        val phone:TextView=view.findViewById(R.id.phone_customer)
        val credit:TextView=view.findViewById(R.id.credit_customer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.customer_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer=listdata[position]
        holder.id.setText(customer.id.toString())
        holder.pwd.setText(customer.pwd)
        holder.name.setText(customer.name)

        if(customer.isVip==0)holder.isvip.setText("否")
        else holder.isvip.setText("是")

        holder.phone.setText(customer.phone)
        holder.credit.setText(customer.credit.toString())

    }
}