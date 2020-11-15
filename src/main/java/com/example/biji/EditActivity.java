package com.example.biji;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class EditActivity extends AppCompatActivity {

    EditText et;
    private String content;
    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        et=findViewById(R.id.et);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_HOME){
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.putExtra("content",et.getText().toString());
            intent.putExtra("time",dateToString());
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String dateToString() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
