package com.kimsunghee.tablayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context, "Helper클래스의 onCreate() 호출", Toast.LENGTH_SHORT).show();
    }

    public void onOpen(SQLiteDatabase db) {
        Toast.makeText(context, "Helper클래스의 onOpen() 호출", Toast.LENGTH_SHORT).show();
        super.onOpen(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Toast.makeText(context, "Helper클래스의 onUpgrade() 호출", Toast.LENGTH_SHORT).show();
        //changeTable(db);
    }
}

