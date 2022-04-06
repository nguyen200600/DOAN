package com.example.doan;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doan.fragment.DatCauHoiFragment;
import com.example.doan.fragment.HomeFragment;
import com.example.doan.fragment.HuongDanHollandFragment;
import com.example.doan.fragment.IntroHollandFragment;
import com.example.doan.fragment.KiemtrabanthanFragment;
import com.example.doan.fragment.LamTestFragment;
import com.example.doan.fragment.MyProfileFragment;
import com.example.doan.fragment.ResultFragment;
import com.example.doan.fragment.ThongtinFragment;
import com.example.doan.fragment.TimnghanhngheFragment;
import com.example.doan.fragment.TimtruongFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class CenterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    public static final int MY_REQUEST_CODE= 10;
    public static final int FRAGMENT_HOME=0;
    public static final int FRAGMENT_THONG_TIN=1;
    public static final int FRAGMENT_TIM_TRUONG=2;
    public static final int FRAGMENT_TIM_NGHANH=3;
    public static final int FRAGMENT_KIEM_TRA=4;
    public static final int FRAGMENT_MY_PROFILE=5;
    public static final int FRAGMENT_DAT_CAU_HOI=6;

    final private MyProfileFragment mMyProfileFragment=new MyProfileFragment();
    private  int mCurrentFragment=FRAGMENT_HOME;
    private NavigationView mNavigationView;
    private ImageView imgAvatar;
    private TextView tvName,tvEmail,tvSDT;
    public String mPhoneNumber;

    final private ActivityResultLauncher<Intent> mActivityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Intent intent=result.getData();
                if(intent==null){
                    return;
                }
                Uri uri=intent.getData();
                mMyProfileFragment.setmUri(uri);
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    mMyProfileFragment.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        initUI();
        getDataPhone();
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        //replaceFragment(new HomeFragment());
        goToFragmentNewHome();
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        showUserInformation();

    }


    private void getDataPhone() {
        mPhoneNumber=getIntent().getStringExtra("phone_number");
        tvSDT.setText(mPhoneNumber);
    }

    private void initUI(){
        mNavigationView=findViewById(R.id.navigation_view);

        imgAvatar=mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvEmail=mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);
        tvName=mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvSDT=mNavigationView.getHeaderView(0).findViewById(R.id.tv_sdt);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_home){
            if(mCurrentFragment!=FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment=FRAGMENT_HOME;
            }
        }else if(id==R.id.nav_thong_tin){
            if(mCurrentFragment!=FRAGMENT_THONG_TIN){
                replaceFragment(new ThongtinFragment());
                mCurrentFragment=FRAGMENT_THONG_TIN;
            }
        }else if(id==R.id.nav_tim_truong){
            if(mCurrentFragment!=FRAGMENT_TIM_TRUONG){
                replaceFragment(new TimtruongFragment());
                mCurrentFragment=FRAGMENT_TIM_TRUONG;
            }
        }else if(id==R.id.nav_tim_nghanh){
            if(mCurrentFragment!=FRAGMENT_TIM_NGHANH){
                replaceFragment(new TimnghanhngheFragment());
                mCurrentFragment=FRAGMENT_TIM_NGHANH;
            }
        }else if(id==R.id.nav_kiem_tra){
            if(mCurrentFragment!=FRAGMENT_KIEM_TRA){
                replaceFragment(new KiemtrabanthanFragment());
                mCurrentFragment=FRAGMENT_KIEM_TRA;
            }
        }else if(id==R.id.nav_sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(CenterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else if(id==R.id.nav_my_profile){
            if(mCurrentFragment!=FRAGMENT_MY_PROFILE){
                replaceFragment(mMyProfileFragment);
                mCurrentFragment=FRAGMENT_MY_PROFILE;
            }
        }else if(id==R.id.nav_dat_cau_hoi){
            if(mCurrentFragment!=FRAGMENT_DAT_CAU_HOI){
                replaceFragment(new DatCauHoiFragment());
                mCurrentFragment=FRAGMENT_DAT_CAU_HOI;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    public void backButton(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(CenterActivity.this);
            builder.setMessage("Bạn có chắc mình muốn thoát khum?");
            builder.setCancelable(true);
            builder.setNegativeButton("Khum", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

    }
    public void goToFragmentKiemtraHolland(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        KiemtrabanthanFragment kiemtrabanthanFragment=new KiemtrabanthanFragment();
        transaction.replace(R.id.content_frame,kiemtrabanthanFragment);
        transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();
    }
    public void goToFragmentDatCauHoi(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        DatCauHoiFragment datCauHoiFragment=new DatCauHoiFragment();
        transaction.replace(R.id.content_frame,datCauHoiFragment);
        transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();
    }
    public void goToFragmentHieuTruong(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        TimtruongFragment timtruongFragment=new TimtruongFragment();
        transaction.replace(R.id.content_frame,timtruongFragment);
        transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();
    }
    public void goToFragmentLamTest(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        LamTestFragment lamTestFragment=new LamTestFragment();
        transaction.replace(R.id.content_frame,lamTestFragment);
        transaction.addToBackStack(HuongDanHollandFragment.TAG);
        transaction.commit();
    }
    public void goToFragmentResult(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        ResultFragment resultFragment=new ResultFragment();
        transaction.replace(R.id.content_frame,resultFragment);
        transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();
    }

    public void goToFragmentHuongdanHolland(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        KiemtrabanthanFragment kiemtrabanthanFragment=new KiemtrabanthanFragment();
        LamTestFragment lamTestFragment=new LamTestFragment();
        transaction.replace(R.id.content_frame,kiemtrabanthanFragment);
        transaction.remove(lamTestFragment);
        transaction.commit();
    }

    public void goToFragmentThongTin(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        ThongtinFragment thongtinFragment=new ThongtinFragment();
        transaction.replace(R.id.content_frame,thongtinFragment);
        transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();
    }
    public void goToFragmentNewHome(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        transaction.replace(R.id.content_frame,homeFragment);
        //transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();

    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.addToBackStack(HomeFragment.TAG);
        transaction.commit();
    }
    public void showUserInformation(){
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        if(name==null){
            tvName.setVisibility(View.GONE);
        }else {
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(name);
        }
        tvEmail.setText(email);
        String phone =user.getPhoneNumber();
        if(phone==null){
            tvSDT.setVisibility(View.GONE);
        }else {
            tvSDT.setText(phone);
            Glide.with(this).load(photoUrl).error(R.drawable.img_user_default).into(imgAvatar);
        }
        Glide.with(this).load(photoUrl).error(R.drawable.img_user_default).into(imgAvatar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }
    public void openGallery(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select picture"));
    }
}