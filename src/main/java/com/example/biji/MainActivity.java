package com.example.biji;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.heweather.plugin.bean.Code;
import com.heweather.plugin.bean.hew.Now;
import com.heweather.plugin.view.HeWeatherConfig;
import com.heweather.plugin.view.HorizonView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
    final String TAG = "tag";
    FloatingActionButton btn;
    TextView tv;
    private Context context=this;
    private ListView lv;
    private List<Note> noteList = new ArrayList<>();
    private NoteAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =(FloatingActionButton)findViewById(R.id.fab);
        tv = (TextView) findViewById(R.id.tv);
        //initNotes();
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(noteList);
        refreshRecyclerView();
        mRecyclerView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                startActivityForResult(intent,0);
            }
        });


        HeWeatherConfig.init("注册到的userId","注册到的key");
        Now now = new Now();
        now.setCode(now.getCode());
        /*
        Now.getWeatherNow(MainActivity.this, "CN101010100", new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
            }

            @Override
            public void onSuccess(List<Now> dataObject) {
                Log.i(TAG, " Weather Now onSuccess: " + new Gson().toJson(dataObject));
            }
        });

         */

    }





    //接受startActivityForResult的结果
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        String content = data.getStringExtra("content");
        String time = data.getStringExtra("time");
        Note note = new Note(content,time,1);
        CRUD crud = new CRUD(context);
        crud.open();
        crud.addNote(note);
        crud.close();
        refreshRecyclerView();
    }
    private void initNotes(){

    }

    public void refreshRecyclerView(){
        CRUD crud = new CRUD(context);
        crud.open();
        if(noteList.size()>0) noteList.clear();
        noteList.addAll(crud.getAllNotes());
        crud.close();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void setListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getContentView() {
        return 0;
    }

}