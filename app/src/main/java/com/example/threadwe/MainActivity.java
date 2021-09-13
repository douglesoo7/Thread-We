package com.example.threadwe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.threadwe.R;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2;
    private WorkerThread workerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        workerThread=new WorkerThread();
        workerThread.start();
    }

    private Runnable taskOne = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(MainActivity.class.getName(),"Task One "+Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable taskTwo = new Runnable() {
        @Override
        public void run() {
            Log.d(MainActivity.class.getName(), "Task Two " + Thread.currentThread().getName());
        }
    };

    private void initViews() {
        button1 = findViewById(R.id.tvData);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workerThread.addTaskToMessageQueue(taskOne);
            }
        });
        button2 = findViewById(R.id.tvTask2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workerThread.addTaskToMessageQueue(taskTwo);
            }
        });
    }


}