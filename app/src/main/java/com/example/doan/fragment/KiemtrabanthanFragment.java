package com.example.doan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.doan.MyViewPagerAdapter;
import com.example.doan.MyViewPagerAdapter2;
import com.example.doan.R;
import com.example.doan.transformer.ZoomOutPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class KiemtrabanthanFragment extends Fragment {
    private View mView;
    public static final String TAG=KiemtrabanthanFragment.class.getName();
    private ViewPager2 mViewPager2;
    private BottomNavigationView mBottomNavigationView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_kiemtrabanthan,container,false);
        initUI();
        mViewPager2.setPageTransformer(new ZoomOutPageTransformer());
        MyViewPagerAdapter2 myViewPagerAdapter=new MyViewPagerAdapter2(this);
        mViewPager2.setAdapter(myViewPagerAdapter);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.bottom_introholland){
                    mViewPager2.setCurrentItem(0);
                }else if(id==R.id.bottom_huongdanholland){
                    mViewPager2.setCurrentItem(1);
                }
                return true;
            }
        });
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_introholland).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_huongdanholland).setChecked(true);
                        break;
                }
            }
        });
        return mView;
    }
    private void initUI() {
        mViewPager2=mView.findViewById(R.id.view_pager_kiemtra);
        mBottomNavigationView=mView.findViewById(R.id.bottom_navigation_kiemtra);
    }


}
