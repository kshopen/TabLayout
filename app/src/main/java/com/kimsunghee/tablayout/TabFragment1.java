package com.kimsunghee.tablayout;

/**
 * Created by Kimsunghee on 2016. 10. 10..
 */

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, null);
        ListView listView = (ListView) view.findViewById(R.id.ListView1);
        ListViewAdapter adapter = new ListViewAdapter(this.getContext(),getMemo());
        listView.setAdapter(adapter);
        return view;
    }

    private ArrayList<Memo> getMemo(){
        Memo memo =  null;
        Cursor cursor = database.rawQuery("SELECT * FROM todoTable", null);
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

    /*
    private ArrayList<Memo> getMemo() {
        ArrayList<Memo> memos = new ArrayList<>();


        // 데이터베이스에 있는 Cursor 객체를 받아서 ArrayList에 담는 작업을 해줘야 한다. 
        Memo memo = new Memo("1");

        memos.add(memo);

        memo = new Memo("2");
        memos.add(memo);

        memo = new Memo("3");
        memos.add(memo);

        return memos;
    }
    */
}