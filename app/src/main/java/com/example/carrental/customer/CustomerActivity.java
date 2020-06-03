package com.example.carrental.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.carrental.R;
import com.google.android.material.tabs.TabLayout;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String myID=intent.getStringExtra("id");

        setContentView(R.layout.activity_customer);
        TabLayout tabLayout=findViewById(R.id.tabLayout);




        final ViewPager viewPager=findViewById(R.id.CustomerViewPager);
        PagerAdapter pagerAdapter=new CustomerPagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount(),myID);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });

    }
}
