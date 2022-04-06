package com.example.doan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.doan.CenterActivity;
import com.example.doan.R;

public class HuongDanHollandFragment extends Fragment {
    private View mView;
    private Button btnStartTest;
    private CenterActivity mCenterActivity;
    public static final String TAG=HuongDanHollandFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_huongdanholland,container,false);
        mCenterActivity=(CenterActivity) getActivity();
        btnStartTest=mView.findViewById(R.id.bt_start_test);
        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCenterActivity.goToFragmentLamTest();
            }
        });
        return mView;
    }
}
