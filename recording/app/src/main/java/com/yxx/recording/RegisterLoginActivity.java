package com.yxx.recording;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterLoginActivity extends AppCompatActivity {
    private TextView pwdLogin;
    private TextView register;
    private EditText user;
    private EditText password;
    private ContentValues value;
   public static   String a;
    public static   String b;
    private CreateDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pwdLogin=findViewById(R.id.userpwd);
        register=findViewById(R.id.register);
        user=findViewById(R.id.phone);
       password=findViewById(R.id.code);
    dbHelper=new CreateDbHelper(this,"cc.db",null,1);
      pwdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterLoginActivity.this,PwdLoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              a=user.getText().toString();
              b=password.getText().toString();

              checkISexist(a);
               // Intent intent=new Intent(RegisterLoginActivity.this, BasicInfoActivity.class);
               // startActivity(intent);

            }
        });

    }
    private void registerUserInfo(String username,String pwd){
        SQLiteDatabase db =dbHelper.getWritableDatabase();
         value=new ContentValues();
        value.put("username",username);
        value.put("pwd",pwd);
        value.put("name","yx");
        value.put("age","18");
        value.put("sex","0");
       db.insert("user1",null,value);
        db.close();
        Toast.makeText(this,"注册成功，请登录！",Toast.LENGTH_SHORT).show();
    }
    private boolean checkISexist(String values){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String query="Select*from user1 where username=?";
        Cursor cursor=db.rawQuery(query,new String[]{values});
        if(cursor.getCount()>0){
            cursor.close();
            Toast.makeText(this,"该用户名已注册，请登录！",Toast.LENGTH_SHORT).show();
            return true;
        }else {
            cursor.close();

            registerUserInfo(a, b);
            return false;
        }
    }
}