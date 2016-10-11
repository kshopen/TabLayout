package com.kimsunghee.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kimsunghee on 2016. 10. 11..
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Memo> memos;
    LayoutInflater layoutInflater;

    ListViewAdapter(Context context, ArrayList<Memo> memos) {
        this.context = context;
        this.memos = memos;
    }
    @Override

    public int getCount() {
        return memos.size();
    }

    @Override
    public Object getItem(int position) {
        return memos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if(view == null) {
            view = layoutInflater.inflate(R.layout.content,viewGroup,false);
            TextView memocontent = (TextView) view.findViewById(R.id.memocontent);
            memocontent.setText(memos.get(i).getMemo());
        }
        return view;
    }
}
