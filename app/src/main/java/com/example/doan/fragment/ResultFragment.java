package com.example.doan.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ResultFragment extends Fragment {
    private View mView;
    private RadarChart radarChart;
    private int score1,score2,score3,score4,score5,score6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_result,container,false);
        receiveDataFromLamTest(score1,score2,score3,score4,score5,score6);
        radarChart=mView.findViewById(R.id.radarChart);
        ArrayList<RadarEntry> visitorForFirstWebsite=new ArrayList<>();
        visitorForFirstWebsite.add(new RadarEntry(score1));
        visitorForFirstWebsite.add(new RadarEntry(score2));
        visitorForFirstWebsite.add(new RadarEntry(score3));
        visitorForFirstWebsite.add(new RadarEntry(score4));
        visitorForFirstWebsite.add(new RadarEntry(score5));
        visitorForFirstWebsite.add(new RadarEntry(score6));

        RadarDataSet radarDataSetForFirstWebsite=new RadarDataSet(visitorForFirstWebsite,"Năng lực của bạn");
        radarDataSetForFirstWebsite.setColor(Color.RED);
        radarDataSetForFirstWebsite.setLineWidth(2f);
        radarDataSetForFirstWebsite.setValueTextColor(Color.RED);
        radarDataSetForFirstWebsite.setValueTextSize(12f);

        RadarData radarData=new RadarData();
        radarData.addDataSet(radarDataSetForFirstWebsite);
        String[] labels={"Realistic","Investigative","Artistic","Social","Enterprising","Conventional"};
        XAxis xAxis=radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setData(radarData);
        return mView;
    }
    public void receiveDataFromLamTest(int d1,int d2, int d3, int d4,int d5,int d6){

    }
}
