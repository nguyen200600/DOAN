package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private RadarChart radarChart;
    private CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
    private CenterActivity mCenterActivity;
    private TextView diemR,diemI,diemA,diemS,diemE,diemC;
    private int score1,score2,score3,score4,score5,score6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score1=getIntent().getIntExtra("d_1_10",5);
        score2=getIntent().getIntExtra("d_11_20",5);
        score3=getIntent().getIntExtra("d_21_30",5);
        score4=getIntent().getIntExtra("d_31_40",5);
        score5=getIntent().getIntExtra("d_41_50",5);
        score6=getIntent().getIntExtra("d_51_60",5);

        diemR=findViewById(R.id.diem_R);
        diemR.setText(score1+"điểm");
        diemI=findViewById(R.id.diem_I);
        diemI.setText(score2+"điểm");
        diemA=findViewById(R.id.diem_A);
        diemA.setText(score3+"điểm");
        diemS=findViewById(R.id.diem_S);
        diemS.setText(score4+"điểm");
        diemE=findViewById(R.id.diem_E);
        diemE.setText(score5+"điểm");
        diemC=findViewById(R.id.diem_C);
        diemC.setText(score6+"điểm");

        cardView1=findViewById(R.id.c1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, R_Activity.class);
                startActivity(intent);
            }
        });
        cardView2=findViewById(R.id.c2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, I_Activity.class);
                startActivity(intent);
            }
        });
        cardView3=findViewById(R.id.c3);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, A_Activity.class);
                startActivity(intent);
            }
        });
        cardView4=findViewById(R.id.c4);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, S_Activity.class);
                startActivity(intent);
            }
        });
        cardView5=findViewById(R.id.c5);
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, E_Activity.class);
                startActivity(intent);
            }
        });
        cardView6=findViewById(R.id.c6);
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, C_Activity.class);
                startActivity(intent);
            }
        });


        radarChart=findViewById(R.id.radarChart);
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
    }


}