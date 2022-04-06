package com.example.doan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doan.fragment.NewsFragment;
import com.example.doan.fragment.ThongtinFragment;
import com.example.doan.fragment.VideosFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {


    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NewsFragment();
            case 1:
                return new VideosFragment();
            default:
                return new NewsFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
