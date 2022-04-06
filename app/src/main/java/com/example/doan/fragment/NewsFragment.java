package com.example.doan.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan.Custom_list;
import com.example.doan.Detail;
import com.example.doan.Entry;
import com.example.doan.ListEntry;
import com.example.doan.ParseXml;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private View mView;
    ListView read ;
    Button btnParseXML;
    public static ListEntry listRssItem;
    public static List<Entry> listNews = new ArrayList<Entry>();
    private String url = "https://tienphong.vn/rss/tuyen-sinh2011-163.rss";
    private ProgressDialog progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_news,container,false);
        Mystask asyscTask = new Mystask();
        asyscTask.execute();
        return mView;
    }
    private class Mystask extends AsyncTask<Void, Void, ListEntry> {

        @Override
        protected ListEntry doInBackground(Void... params) {
            try {
                listRssItem = new ListEntry();
                ParseXml parse = new ParseXml(url);
                listRssItem = parse.parseXMLRSS();
                listNews = listRssItem.getListEntry();
                Log.d("//=================", "Tong so phan tu RSS" + listRssItem.getListEntry().size());
                for (int i = 0; i < listNews.size(); i++) {
                    Entry en = listNews.get(i);
                    Log.i("//===================", "//========="+en.getTitle());
                }

            } catch (Exception e) {
                listRssItem = null;
                Log.i("Error", "Here");
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    read = (ListView) mView.findViewById(R.id.listread);

                    Custom_list adt = new Custom_list(getActivity(),R.layout.custom_new,NewsFragment.listNews);
                    read.setAdapter(adt);
                    read.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), Detail.class);
                            intent.putExtra("link",listNews.get(position).link);
                            startActivity(intent);
                        }
                    });
                }
            });


            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ListEntry result) {
// TODO Auto-generated method stub
            Log.i("", "//=============//onPostExecute");
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
// TODO Auto-generated method stub
            Log.i("", "//=============//onPreExecute");
            progressBar = ProgressDialog.show(getActivity(), "",
                    "please wait for checking data...");
            super.onPreExecute();
        }
    }
    public static boolean isConnected(Context context) {
        ConnectivityManager
                cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }
}


