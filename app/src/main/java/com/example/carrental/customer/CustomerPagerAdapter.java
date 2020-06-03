package com.example.carrental.customer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.carrental.customer.frag1.CustomerFragment1;
import com.example.carrental.customer.frag2.CustomerFragment2;
import com.example.carrental.customer.frag3.CustomerFragment3;

public class CustomerPagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    private String myID;
    public CustomerPagerAdapter(@NonNull FragmentManager fm, int numOfTabs,String myID) {
        super(fm);
        this.numOfTabs=numOfTabs;
        this.myID=myID;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CustomerFragment1(myID);

            case 1:
                return new CustomerFragment2(myID);

            case 2:
                return new CustomerFragment3(myID);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
