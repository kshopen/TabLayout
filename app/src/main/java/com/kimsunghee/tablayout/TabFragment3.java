package com.kimsunghee.tablayout;

/**
 * Created by Kimsunghee on 2016. 10. 10..
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TabFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, null);
        ListView listView = (ListView) view.findViewById(R.id.ListView1);
        ListViewAdapter adapter = new ListViewAdapter(this.getContext(),getMemo());
        listView.setAdapter(adapter);
        return view;
    }

    private ArrayList<Memo> getMemo() {
        ArrayList<Memo> memos = new ArrayList<>();

        Memo memo = new Memo("7");

        memos.add(memo);

        memo = new Memo("8");
        memos.add(memo);

        memo = new Memo("9");
        memos.add(memo);

        return memos;
    }
}
