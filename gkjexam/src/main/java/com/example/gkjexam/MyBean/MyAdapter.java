package com.example.gkjexam.MyBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gkjexam.R;

import java.util.List;

/**
 * Created by 郭康杰 on 2017/8/17.
 */
public class MyAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater inflater;
    private int[] picture={R.mipmap.shaonian,R.mipmap.qingnian,R.mipmap.shaonv,R.mipmap.danmei};

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Handler handler = null;
        if(convertView==null){
             handler = new Handler();
            convertView = inflater.inflate(R.layout.griditem, null);
            handler.iv_item1 = (ImageView) convertView.findViewById(R.id.iv_item1);
            handler.tv_item1 = (TextView) convertView.findViewById(R.id.tv_item1);
            convertView.setTag(handler);
        }else{
            handler= (Handler) convertView.getTag();
        }
        String s = list.get(position);
        handler.iv_item1.setImageResource(picture[position]);
        handler.tv_item1.setText(s);
        return convertView;
    }
    class Handler{
        ImageView iv_item1;
        TextView tv_item1;;
    }
}
