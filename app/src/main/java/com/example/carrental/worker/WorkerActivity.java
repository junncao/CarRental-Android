package com.example.carrental.worker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carrental.R;
import com.example.carrental.worker.car.CarInfoActivity;
import com.example.carrental.worker.customer.Customer;
import com.example.carrental.worker.customer.CustomerInfoActivity;
import com.example.carrental.worker.finish.FinishActivity;
import com.example.carrental.worker.income.IncomeActivity;
import com.example.carrental.worker.review.ReviewActivity;
import com.example.carrental.worker.workerInfo.WorkerInfoActivity;

public class WorkerActivity extends AppCompatActivity {
    String myID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        myID=intent.getStringExtra("id");
        setContentView(R.layout.activity_worker);
    }

    public void onCarInfoClicked(View view) {
        Intent intent=new Intent(WorkerActivity.this, CarInfoActivity.class);
        intent.putExtra("id",myID);
        startActivity(intent);
    }

    public void onCusInfoClicked(View view) {
        Intent intent=new Intent(WorkerActivity.this, CustomerInfoActivity.class);
        intent.putExtra("id",myID);
        startActivity(intent);
    }
    public void onWokerInfoClicked(View view) {
        Intent intent=new Intent(WorkerActivity.this, WorkerInfoActivity.class);
        intent.putExtra("id",myID);
        startActivity(intent);
    }
    public void onIncomeInfoClicked(View view) {
        Intent intent=new Intent(WorkerActivity.this, IncomeActivity.class);
        intent.putExtra("id",myID);
        startActivity(intent);
    }

    public void onFinishInoClicked(View view) {
        Intent intent=new Intent(WorkerActivity.this, FinishActivity.class);
        intent.putExtra("id",myID);
        startActivity(intent);
    }

    public void onReviewInfoClicked(View view) {
        Intent intent=new Intent(WorkerActivity.this, ReviewActivity.class);
        intent.putExtra("id",myID);
        startActivity(intent);
    }


}
