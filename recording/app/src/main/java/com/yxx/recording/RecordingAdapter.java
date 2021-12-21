package com.yxx.recording;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.ViewHolder> {
    private List<RecordingText>recordingTextList;
    private Context context;
    public static String item_title;
    public static String item_content;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        View Box;
        public ViewHolder(View view){
            super(view);
            Box=view;
            title=view.findViewById(R.id.content);
        }
    }
    public RecordingAdapter(List<RecordingText>mRecordingTextList){
        recordingTextList=mRecordingTextList;
    }
    @NonNull
    @Override
    public RecordingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        Log.d("11111","12131414");
        holder.Box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position=holder.getAdapterPosition();
                RecordingText text=recordingTextList.get(position);
                String mitem_title=text.getTitle();
                queryInfo(mitem_title);
                Intent intent=new Intent(context,ReadActivity.class);
                context.startActivity(intent);



            }
        });
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecordingAdapter.ViewHolder holder, int position) {
       RecordingText text=recordingTextList.get(position);
        holder.title.setText(text.getTitle());
         context=holder.itemView.getContext();

    }

    @Override
    public int getItemCount() {
        return recordingTextList.size();
    }
    @SuppressLint("Range")
    private  void queryInfo(String values){
        ContentHelper contentHelper=new ContentHelper(context,"y.db",null,1);
        SQLiteDatabase db=contentHelper.getWritableDatabase();
        String query="Select*from content where title=?";
        Cursor cursor=db.rawQuery(query,new String[]{values});
        if (cursor.moveToFirst()) {
            do {
                 item_title= cursor.getString(cursor.getColumnIndex("title"));
                item_content = cursor.getString(cursor.getColumnIndex("content"));

            } while (cursor.moveToNext());
        }
        cursor.close();

    }
}
