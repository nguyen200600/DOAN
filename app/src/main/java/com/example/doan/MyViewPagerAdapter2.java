package com.example.doan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doan.fragment.HuongDanHollandFragment;
import com.example.doan.fragment.IntroHollandFragment;
import com.example.doan.fragment.NewsFragment;
import com.example.doan.fragment.VideosFragment;

public class MyViewPagerAdapter2 extends FragmentStateAdapter {


    public MyViewPagerAdapter2(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new IntroHollandFragment();
            case 1:
                return new HuongDanHollandFragment();
            default:
                return new IntroHollandFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
