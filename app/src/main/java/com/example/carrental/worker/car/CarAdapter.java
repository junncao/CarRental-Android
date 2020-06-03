package com.example.carrental.worker.car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;
import com.example.carrental.customer.frag1.RentalCarAdapter;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<Car> listdata;
    private Context mContext;

    public CarAdapter(List<Car> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View listItem=layoutInflater.inflate(R.layout.car_item,parent,false);
        CarAdapter.ViewHolder viewHolder=new CarAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car item=listdata.get(position);
        holder.id_car.setText(Integer.toString(item.id));
        holder.license_car.setText(item.license);
        holder.brand_car.setText(item.brand);
        switch (item.state){
            case 1:
                holder.state_car.setText("全新");
                break;
            case 2:
                holder.state_car.setText("九五新");
                break;
            case 3:
                holder.state_car.setText("九成新");
            case 4:
                holder.state_car.setText("八成新");
            case 5:
                holder.state_car.setText("检修");
        }
        if(item.valid==1){
            holder.valid_car.setText("空闲");
        }else{
            holder.valid_car.setText("被租用");
        }
        holder.rent_car.setText(Integer.toString(item.rent));
        holder.deposit_car.setText(Integer.toString(item.deposit));

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_car;
        TextView license_car;
        TextView brand_car;
        TextView state_car;
        TextView valid_car;
        TextView rent_car;
        TextView deposit_car;

        public ViewHolder(@Nullable View v) {
            super(v);
            this.id_car = v.findViewById(R.id.id_car);
            this.license_car = v.findViewById(R.id.license_car);
            this.brand_car = v.findViewById(R.id.brand_car);
            this.state_car = v.findViewById(R.id.state_car);
            this.valid_car = v.findViewById(R.id.valid_car);
            this.rent_car = v.findViewById(R.id.rent_car);
            this.deposit_car = v.findViewById(R.id.deposit_car);
        }
    }
}
