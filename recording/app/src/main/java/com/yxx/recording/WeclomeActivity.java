package com.yxx.recording;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class WeclomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_weclome);
        handler.sendEmptyMessageDelayed(0,3000);

    }
    public Handler handler=new Handler(){
        public void handleMessage(Message msg){
            getHome();
            super.handleMessage(msg);
        }
    };
    public void getHome(){
        Intent intent=new Intent(WeclomeActivity.this,PwdLoginActivity.class);
        startActivity(intent);
        finish();
    }
}