package com.yxx.recording;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<RecordingText>recordingTextList=new ArrayList<>();
    private CreateDbHelper createDbHelper;
    private ContentHelper contentHelper;
    private SQLiteDatabase db;

    private ImageView edit;
    private RecyclerView recyclerView;
    private String title;
    private String content;
    private String username1;
    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=findViewById(R.id.edit);
        recyclerView=findViewById(R.id.recyclerview);
        exit=findViewById(R.id.me);
     //  upData();
        loadData();
        /*startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(0,0);*/
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        RecordingAdapter adapter=new RecordingAdapter(recordingTextList);
        recyclerView.setAdapter(adapter);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(MainActivity.this,WriteActivity.class);
                startActivityForResult(intent,1);

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(MainActivity.this,PwdLoginActivity.class);
                startActivity(intent);

            }
        });

    }
    @SuppressLint("Range")
    private void loadData(){
        contentHelper=new ContentHelper(this,"y.db",null,1);
        db=contentHelper.getWritableDatabase();
        String query="Select*from content where username=?";
        db.rawQuery(query,new String[]{PwdLoginActivity.input_id});
        Cursor cursor=db.query("content",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
               title=cursor.getString(cursor.getColumnIndex("title"));
              content=cursor.getString(cursor.getColumnIndex("content"));
              username1=cursor.getString(cursor.getColumnIndex("username"));
              RecordingText text=new RecordingText(title,content);
              recordingTextList.add(text);

            }while (cursor.moveToNext());
        }
        cursor.close();

    }
    private void upData(){
        contentHelper=new ContentHelper(this,"y.db",null,1);
        db=contentHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title","今日卡路里");
        contentValues.put("content","1千焦");
        contentValues.put("username",PwdLoginActivity.input_id);
        db.insert("content",null,contentValues);
    }

    @SuppressLint("Range")
   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            contentHelper=new ContentHelper(this,"y.db",null,1);
            db=contentHelper.getWritableDatabase();
            String query="Select*from content where username=?";
            db.rawQuery(query,new String[]{PwdLoginActivity.input_id});
            Cursor cursor=db.query("content",null,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do {
                    title=cursor.getString(cursor.getColumnIndex("title"));
                    content=cursor.getString(cursor.getColumnIndex("content"));
                    username1=cursor.getString(cursor.getColumnIndex("username"));
                    RecordingText text=new RecordingText(title,content);
                    recordingTextList.add(text);
                }while (cursor.moveToNext());
            }
            cursor.close();


        }else if (requestCode == 2) {
            contentHelper=new ContentHelper(this,"y.db",null,1);
            db=contentHelper.getWritableDatabase();
            String query="Select*from content where username=?";
            db.rawQuery(query,new String[]{PwdLoginActivity.input_id});
            Cursor cursor=db.query("content",null,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do {
                    title=cursor.getString(cursor.getColumnIndex("title"));
                    content=cursor.getString(cursor.getColumnIndex("content"));
                    username1=cursor.getString(cursor.getColumnIndex("username"));
                    RecordingText text=new RecordingText(title,content);
                    recordingTextList.add(text);
                }while (cursor.moveToNext());
            }
            cursor.close();

        }else if (requestCode == 4) {
            loadData();
            LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            RecordingAdapter adapter=new RecordingAdapter(recordingTextList);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(this,"退出程序",Toast.LENGTH_SHORT).show();
        finish();


    }


}