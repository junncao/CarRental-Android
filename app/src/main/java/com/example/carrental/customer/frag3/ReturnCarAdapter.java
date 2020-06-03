package com.example.carrental.customer.frag3;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;
import com.example.carrental.login.MyDatabaseHelper;

import java.util.List;

public class ReturnCarAdapter extends RecyclerView.Adapter<ReturnCarAdapter.ViewHolder> {
    private List<ReturnCarModel> listdata;
    private String myID;
    private Context mContext;
    public ReturnCarAdapter(List<ReturnCarModel> listdata, String myID) {
        this.listdata = listdata;
        this.myID=myID;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View listItem=layoutInflater.inflate(R.layout.return_car_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ReturnCarModel myListData= listdata.get(position);
        holder.id_return_car.setText(listdata.get(position).getRentalID());
        holder.license_return_car.setText(listdata.get(position).getLicense());
        holder.brand_return_car.setText(listdata.get(position).getBrand());
        if(listdata.get(position).getState()==0){
            holder.return_btn.setText("审核中");
            holder.return_btn.setEnabled(false);
        }
        else{
            holder.return_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Toast.makeText(v.getContext(),"click on item"+position,Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(mContext);

                    // Set the dialog title and message.
                    myAlertBuilder.setTitle("提示");
                    myAlertBuilder.setMessage("确定要归还这辆车吗？");

                    // Add the dialog buttons.
                    myAlertBuilder.setPositiveButton("确认",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String rentalID=listdata.get(position).getRentalID();
                                    MyDatabaseHelper dbHelper = new MyDatabaseHelper(mContext, "CustomerStore.db", null, 1);
                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    ContentValues updateValues=new ContentValues();
                                    updateValues.put("state",1);
                                    db.update(
                                            "rental",
                                            updateValues,
                                            "id=?",
                                            new String[]{rentalID}

                                    );

                                    Toast.makeText(mContext,
                                        "还车中...",
                                        Toast.LENGTH_SHORT).show();

                                Toast.makeText(mContext,
                                        "还车成功，等待审核!",
                                        Toast.LENGTH_SHORT).show();
                                holder.return_btn.setText("审核中");
                                holder.return_btn.setEnabled(false);

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
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id_return_car;
        public TextView license_return_car;
        public TextView brand_return_car;
        public Button return_btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_return_car=itemView.findViewById(R.id.id_return_car);
            license_return_car=itemView.findViewById(R.id.license_return_car);
            brand_return_car=itemView.findViewById(R.id.brand_return_car);
            return_btn= itemView.findViewById(R.id.return_btn);

        }
    }
}
