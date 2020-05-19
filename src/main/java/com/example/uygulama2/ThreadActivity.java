package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class ThreadActivity extends AppCompatActivity {
    private Button downloadBttn;
    ProgressBar progressBar;
    private ImageView img;
    Random r = new Random(); //random sınıfı
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        downloadBttn = findViewById(R.id.downloadButton);
        progressBar = findViewById(R.id.progressBar);
        img = findViewById(R.id.imageView);
        img.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setMax(100);
        downloadBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                myAsyncTask task = new myAsyncTask();
                task.setProgressBar(progressBar);
                task.execute();

            }
        });
    }

    public class myAsyncTask extends android.os.AsyncTask<Integer, Integer, String> {
        ProgressBar pb;

        public void setProgressBar(ProgressBar progressBar) {
            this.pb = progressBar;
        }

        @Override
        protected String doInBackground(Integer[] objects) {
            while(x<100) {
                try {
                        x = x + r.nextInt(10);
                        publishProgress(x);
                        Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        protected void onProgressUpdate(Integer[] values) {
            pb.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onProgressUpdate();
            img.setVisibility(View.VISIBLE);
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        }
    }
}
