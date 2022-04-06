package com.example.doan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapterUniversity extends FirebaseRecyclerAdapter<ModelUniversity, MyAdapterUniversity.myviewholder>
{
    public MyAdapterUniversity(@NonNull FirebaseRecyclerOptions<ModelUniversity> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ModelUniversity model)
    {
        holder.name.setText(model.getTen());
        holder.diachitruong.setText(model.getDiachi());
        holder.sdt.setText(model.getSdt());
        Glide.with(holder.img.getContext()).load(model.getUrl()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,diachitruong,sdt;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            diachitruong=(TextView)itemView.findViewById(R.id.diachi_truong);
            sdt=(TextView)itemView.findViewById(R.id.sdt_truong);
        }
    }
}
