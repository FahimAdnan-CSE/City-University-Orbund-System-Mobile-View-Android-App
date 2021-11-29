package com.example.myproject12;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.myapplication.R;

public class splash_screen extends AppCompatActivity {

    private ProgressBar progressBar;
    private  int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       requestWindowFeature(Window.FEATURE_NO_TITLE); //full window view
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//

        setContentView(R.layout.activity_splash_screen);


        ActionBar actionBar=getSupportActionBar(); //how to remove action bar in android studio from specific activity
        // actionBar.isShowing();  //action bar is show in activity
        actionBar.hide();//action bar is hide from activity




        progressBar= (ProgressBar) findViewById(R.id.progressbarid);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                progressBarwork();
                newactivity();

            }
        });

        thread.start();



    }

    public  void progressBarwork()
    {

        for (progress=10;progress<=100;progress=progress+25)
        {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    }

    public void newactivity()
    {

        Intent intent=new Intent(splash_screen.this,MainActivity.class);
        startActivity(intent);
        finish();


    }




}
