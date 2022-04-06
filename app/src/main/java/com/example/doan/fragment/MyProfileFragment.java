package com.example.doan.fragment;

import static com.example.doan.CenterActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.doan.CenterActivity;
import com.example.doan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MyProfileFragment extends Fragment {
    private Uri mUri;
    private View mView;
    private ImageView imgAvatar;
    private EditText edtFullName,edtEmail;
    private Button btnUpdateProfile;
    private CenterActivity mCenterActivity;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_my_profile,container,false);
        initUI();
        mCenterActivity=(CenterActivity) getActivity();
        progressDialog=new ProgressDialog(getActivity());
        setUserInformation();
        initListener();
        return mView;
    }


    private void initListener() {
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateProfile();
            }
        });
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        progressDialog.show();
        String strFullName=edtFullName.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullName)
                .setPhotoUri(mUri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                            mCenterActivity.showUserInformation();
                        }
                    }
                });
    }

    private void onClickRequestPermission(){
        if(mCenterActivity==null){
            return;
        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mCenterActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mCenterActivity.openGallery();
        }else{
            String []permission={Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permission,MY_REQUEST_CODE);
        }
    }
    public void setBitmapImageView(Bitmap bitmapImageView){
        imgAvatar.setImageBitmap(bitmapImageView);
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    private void setUserInformation() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }else {
            edtFullName.setText(user.getDisplayName());
            edtEmail.setText(user.getEmail());
            Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.img_user_default).into(imgAvatar);
        }
    }

    private void initUI(){
        imgAvatar=mView.findViewById(R.id.image_Avatar);
        edtFullName=mView.findViewById(R.id.edt_full_name);
        edtEmail=mView.findViewById(R.id.edtext_email);
        btnUpdateProfile=mView.findViewById(R.id.btn_update_profile);
    }
}
