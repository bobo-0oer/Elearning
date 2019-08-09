package com.example.elearning.Home_Fragment.My.SetButton;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elearning.R;

import static com.example.elearning.Home_Fragment.Home.Fragment_Home.COMPLETE_TIME;
import static com.example.elearning.Home_Fragment.Home.Fragment_Home.LEARNING_WORD;
import static com.example.elearning.Home_Fragment.Home.Fragment_Home.TODAYWORD;

public class ProgressActivity extends AppCompatActivity {

    private ImageView exitProgressIV=null;
    private TextView completeProgressTV=null;
    private TextView completeTimeTV=null;
    private ProgressBar progressBar1=null;
    private ProgressBar progressBar2=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitProgressIV=(ImageView)findViewById(R.id.exitProgressIV);
        completeProgressTV=(TextView)findViewById(R.id.completeProgressTV);
        completeTimeTV=(TextView)findViewById(R.id.completeTimeTV);
        progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);

        completeProgressTV.setText(LEARNING_WORD);
        completeTimeTV.setText(COMPLETE_TIME);

        int progress=((Integer.parseInt(LEARNING_WORD))*100)/1000;
        progressBar1.setProgress(progress);
        progressBar2.setProgress(Integer.parseInt(TODAYWORD));


        exitProgressIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
