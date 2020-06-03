package com.example.carrental.customer.frag1;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;
import com.example.carrental.login.MyDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RentalCarAdapter extends RecyclerView.Adapter<RentalCarAdapter.ViewHolder> {
    private List<RentalCarModel> listdata;
    private String myID;
    private Context mContext;
    public RentalCarAdapter(List<RentalCarModel> listdata,String myID) {
        this.listdata = listdata;
        this.myID=myID;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View listItem=layoutInflater.inflate(R.layout.rental_car_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final RentalCarModel myListData= listdata.get(position);
        holder.id_rental_car.setText(listdata.get(position).getId());
        holder.license_rental_car.setText(listdata.get(position).getLicense());
        holder.brand_rental_car.setText(listdata.get(position).getBrand());
        holder.rent_rental_car.setText(listdata.get(position).getRent());
        holder.deposit_rental_car.setText(listdata.get(position).getDeposit());
        holder.rentbtn_rental_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"click on item"+position,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder myAlertBuilder =
                        new AlertDialog.Builder(mContext);

                // Set the dialog title and message.
                myAlertBuilder.setTitle("确定要租这辆车吗？");
                myAlertBuilder.setMessage("这辆车租金是"+listdata.get(position).getRent()+"元,押金是"+listdata.get(position).getDeposit()+"元");

                // Add the dialog buttons.
                myAlertBuilder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String carID=listdata.get(position).getId();
                                MyDatabaseHelper dbHelper = new MyDatabaseHelper(mContext, "CustomerStore.db", null, 1);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();

                                ContentValues valuesInsert=new ContentValues();
                                valuesInsert.put("customerID",myID);
                                valuesInsert.put("carID",carID);
                                valuesInsert.put("state",0);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date();
                                valuesInsert.put("datetime",dateFormat.format(date).toString());
                                db.insert("rental",null,valuesInsert);

                                ContentValues valuesUpdate=new ContentValues();
                                valuesUpdate.put("valid",0);
                                db.update("car",valuesUpdate,"id=?",
                                        new String[]{carID});
                                listdata.remove(position);
                                notifyItemRemoved(position);
                                // User clicked OK button.

                                Toast.makeText(mContext,
                                        "支付中....",
                                        Toast.LENGTH_SHORT).show();
                                Toast.makeText(mContext,
                                        "成功租用!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                myAlertBuilder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.

                            }
                        });
                // Create and show the AlertDialog.
                myAlertBuilder.show();



            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id_rental_car;
        public TextView license_rental_car;
        public TextView brand_rental_car;
        public TextView rent_rental_car;
        public TextView deposit_rental_car;
        public Button rentbtn_rental_car;
        public LinearLayout item_rental_car;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_rental_car=itemView.findViewById(R.id.id_rental_car);
            license_rental_car=itemView.findViewById(R.id.license_rental_car);
            brand_rental_car=itemView.findViewById(R.id.brand_rental_car);
            rent_rental_car=itemView.findViewById(R.id.rent_rental_car);
            deposit_rental_car= itemView.findViewById(R.id.deposit_rental_car);
            rentbtn_rental_car= itemView.findViewById(R.id.rentbtn_rental_car);
            item_rental_car=itemView.findViewById(R.id.item_rental_car);
        }
    }
}
