package com.example.doan.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan.CenterActivity;
import com.example.doan.R;
import com.example.doan.databinding.ActivityMainBinding;
import com.example.doan.databinding.FragmentDatcauhoiBinding;

public class DatCauHoiFragment extends Fragment {
    private View mView;
    EditText etSubject,etMessage;
    Button btSend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_datcauhoi,container,false);

        initUI();

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject=etSubject.getText().toString();
                String message=etMessage.getText().toString();
                String[] addresses={"nguyenbui200600@gmail.com"};
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                if(intent.resolveActivity(getContext().getPackageManager())!= null){
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "No app is installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mView;
    }

    private void initUI() {
        etSubject=mView.findViewById(R.id.et_subject);
        etMessage=mView.findViewById(R.id.et_message);
        btSend=mView.findViewById(R.id.bt_send);
    }
}
