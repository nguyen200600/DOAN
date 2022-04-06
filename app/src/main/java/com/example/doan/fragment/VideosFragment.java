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

import com.example.doan.R;
import com.example.doan.youtube_player.YoutubeAdapter;
import com.example.doan.youtube_player.YoutubeModel;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment {
    private View mView;
    RecyclerView recyclerView;
    YoutubeAdapter adapter;
    List<YoutubeModel> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_videos,container,false);
        recyclerView=mView.findViewById(R.id.recycler_view);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter=new YoutubeAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        list.add(new YoutubeModel("ukZgKzEq6kY"));
        list.add(new YoutubeModel("1z33vWm6qGI"));
        list.add(new YoutubeModel("aycL6VcCD48"));
        list.add(new YoutubeModel("ihpYWE5nhLI"));
        list.add(new YoutubeModel("50XWsA35bfs"));
        list.add(new YoutubeModel("xKVFFtQigZc"));
        list.add(new YoutubeModel("l044KKTOmto"));
        list.add(new YoutubeModel("lF9Vgv2W3jU"));
        list.add(new YoutubeModel("SwPGYvhbe8k"));
        list.add(new YoutubeModel("ov6VZGT2oik"));
        return mView;
    }
}
