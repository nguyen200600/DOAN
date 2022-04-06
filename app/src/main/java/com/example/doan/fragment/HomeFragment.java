package com.example.doan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.CenterActivity;
import com.example.doan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {
    private View mView;
    private LinearLayout mGotoThongTin,mThoat,mGotoKiemTra,mGotoDatCauHoi,mGotoTimTruong;
    private CenterActivity mCenterActivity;
    private TextView textView;
    public static final String TAG=HomeFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_home,container,false);
        initUI();
        mCenterActivity=(CenterActivity) getActivity();
        initListener();
        setTen();
        return mView;
    }

    private void setTen() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        textView.setText(user.getDisplayName());
    }


    private void initListener() {
        mGotoThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCenterActivity.goToFragmentThongTin();
            }
        });
        mGotoKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCenterActivity.goToFragmentKiemtraHolland();
            }
        });
        mGotoDatCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCenterActivity.goToFragmentDatCauHoi();
            }
        });
        mGotoTimTruong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCenterActivity.goToFragmentHieuTruong();
            }
        });
        mThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCenterActivity.backButton();
            }
        });
  }

    private void initUI() {
        mGotoThongTin=mView.findViewById(R.id.goToFragment);
        mGotoDatCauHoi=mView.findViewById(R.id.goToFragmentDCH);
        mGotoKiemTra=mView.findViewById(R.id.goToFragmentKT);
        mGotoTimTruong=mView.findViewById(R.id.goToFragmentTimTruong);
        textView=mView.findViewById(R.id.ten);
        mThoat=mView.findViewById(R.id.lo_thoat);

    }



}
