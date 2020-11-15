package com.example.biji;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final String CONTENT ="content" ;
    public static final String ID ="_id" ;
    public static final String TIME ="time" ;
    public static final String MODE ="mode" ;

    public static final String CREATE_NOTE="create table note ("
            +"ID integer primary key autoincrement,"
            +"CONTENT text not null,"
            +"TIME text not null,"
            +"MODE integer default 1)";

    private Context mContext;

    public NoteDatabase(@Nullable Context context) {
        super(context,CREATE_NOTE,null,1);
        mContext = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            for (int i=oldVersion;i<newVersion;i++){
                switch (i){
                    case 1:
                        break;
                    case 2:
                        //updateNote(db);
                    default:
                        break;
                }
            }


    }
}
