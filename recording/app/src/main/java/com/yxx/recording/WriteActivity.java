package com.yxx.recording;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {
    private ImageView back;

    private EditText write;
    private EditText write_title;
    private ImageView save;
    private ContentHelper contentHelper;
    private SQLiteDatabase db;
    private  String title;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        back=findViewById(R.id.back);

        save=findViewById(R.id.save);
        write=findViewById(R.id.write);
        write_title=findViewById(R.id.write_title);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(WriteActivity.this,MainActivity.class);
               startActivityForResult(intent,2);
            }
        });
        contentHelper=new ContentHelper(this,"y.db",null,1);
        db=contentHelper.getWritableDatabase();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               text=write.getText().toString();
               title=write_title.getText().toString();
               checkSaveexist(title);

            }
        });

    }
    public boolean checkSaveexist(String values){
        db=contentHelper.getWritableDatabase();
        title=write_title.getText().toString();
       String query="Select*from content where title=?";
        Cursor cursor=db.rawQuery(query,new String[]{values});
        if(cursor.getCount()>0){
            cursor.close();
            Toast.makeText(this,"该标题已存在，请重新输入！",Toast.LENGTH_SHORT).show();
            return true;
        }else {
            cursor.close();
            ContentValues contentValues=new ContentValues();
            contentValues.put("username",PwdLoginActivity.input_id);
            contentValues.put("title",title);
            contentValues.put("content",text);
            db.insert("content",null,contentValues);
            Toast.makeText(WriteActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
  /*  public boolean checkDeleteExist(String values){
        db=contentHelper.getWritableDatabase();
        title=write_title.getText().toString();
        String query="Select*from content where title=?";
        Cursor cursor=db.rawQuery(query,new String[]{values});
        if(cursor.getCount()>0){
            db.execSQL("delete from content where title=?",new String[]{values});
            cursor.close();
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }else {
            cursor.close();
            Toast.makeText(this,"该标题不存在或还未保存，请保存后重试！",Toast.LENGTH_SHORT).show();
            return false;
        }

    }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(WriteActivity.this,MainActivity.class);
        startActivityForResult(intent,2);
        finish();
    }


}