package com.example.doan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.ModelUniversity;
import com.example.doan.MyAdapterUniversity;
import com.example.doan.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class TimtruongFragment extends Fragment {
    private View mView;
    private RecyclerView recview;
    MyAdapterUniversity adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_timtruong,container,false);
        recview=(RecyclerView) mView.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<ModelUniversity> options =
                new FirebaseRecyclerOptions.Builder<ModelUniversity>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Universities"), ModelUniversity.class)
                        .build();

        adapter=new MyAdapterUniversity(options);
        recview.setAdapter(adapter);
        return mView;
    }
}
