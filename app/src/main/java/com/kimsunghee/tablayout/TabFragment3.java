package com.kimsunghee.tablayout;

/**
 * Created by Kimsunghee on 2016. 10. 10..
 */
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TabFragment3 extends Fragment {

    private SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // DB Open 코드
        SQLiteOpenHelper opener = new SQLiteOpenHelper(this.getContext(), "memo1.db", null, 1) {

            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                database.beginTransaction();
                try {
                    if (database != null) {
                        SQLiteStatement stmt = database.compileStatement(
                                "CREATE TABLE if not exists " + "debtTable" + "("
                                        + "_id integer PRIMARY KEY autoincrement, "
                                        + "memo text"
                                        + ")");
                        stmt.execute();
                        database.setTransactionSuccessful();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase database, int i, int i1) {
                // changeTable();
            }

            @Override
            public void onOpen(SQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        database = opener.getWritableDatabase();

        View view = inflater.inflate(R.layout.tab_fragment_3, null);
        ListView listView = (ListView) view.findViewById(R.id.ListView3);
        ListViewAdapter adapter = new ListViewAdapter(this.getContext(),getMemo());
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return view;
    }

    private ArrayList<Memo> getMemo(){
        Memo memo =  null;
        Cursor cursor = database.rawQuery("SELECT * FROM debtTable", null);
        cursor.moveToFirst();
        ArrayList<Memo> memos = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            memo = new Memo(cursor.getString(cursor.getColumnIndex("memo")));
            memos.add(memo);
            cursor.moveToNext();
        }
        cursor.close();
        return memos;
    }
}
