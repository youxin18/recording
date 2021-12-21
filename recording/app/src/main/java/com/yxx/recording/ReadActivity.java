package com.yxx.recording;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private ImageView back_read;
    private ImageView save_edit;
    private ImageView delete_read;
    private TextView read_title;
    private TextView read_content;
    private String readTitle;
    private ContentHelper contentHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);
        back_read=findViewById(R.id.back_read);
        save_edit=findViewById(R.id.save_edit);
        delete_read=findViewById(R.id.delete_read);
        read_title=findViewById(R.id.read_title);
        read_content=findViewById(R.id.read_content);
        read_title.setText(RecordingAdapter.item_title);
        read_content.setText(RecordingAdapter.item_content);
        back_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(ReadActivity.this,MainActivity.class);
               startActivity(intent);

            }
        });
        save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReadActivity.this,WriteActivity.class);
               startActivity(intent);
            }
        });
        delete_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeleteExist(read_title.getText().toString());


            }
        });
    }

    public boolean checkDeleteExist(String values){
        contentHelper=new ContentHelper(this,"y.db",null,1);
        db=contentHelper.getWritableDatabase();
        readTitle=read_title.getText().toString();
        String query="Select*from content where title=?";
        Cursor cursor=db.rawQuery(query,new String[]{values});
        if(cursor.getCount()>0){
            db.execSQL("delete from content where title=?",new String[]{values});
            cursor.close();
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();

           Intent intent=new Intent(ReadActivity.this,MainActivity.class);
            startActivityForResult(intent,1);
            finish();
          /*Intent intent=new Intent(ReadActivity.this,MainActivity.class);
           startActivity(intent);*/
            return true;

        }else {
            cursor.close();
            Toast.makeText(this,"该标题不存在或还未保存，请保存后重试！",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ReadActivity.this,MainActivity.class);
        startActivity(intent);
    }
}