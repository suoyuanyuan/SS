package com.example.gkjexam.MyBean;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gkjexam.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by 郭康杰 on 2017/8/18.
 */
public class MyCariAdapter extends BaseAdapter{
    private ArrayList<MyCariBean> list;
    private Context context;
    private LayoutInflater inflater;
    private DisplayImageOptions options;


    public MyCariAdapter(ArrayList<MyCariBean> list, Context context) {
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
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        Handler handler =null;
        if (convertView==null){
            handler = new Handler();
            convertView = inflater.inflate(R.layout.caricature_item,null);
            handler.iv_caricature = (ImageView) convertView.findViewById(R.id.iv_caricature);
            handler.caricature_name = (TextView) convertView.findViewById(R.id.caricature_name);
            handler.caricature_address = (TextView) convertView.findViewById(R.id.caricature_address);
            handler.caricature_lx = (TextView) convertView.findViewById(R.id.caricature_lx);
            handler.caricature_time = (TextView) convertView.findViewById(R.id.caricature_time);
            convertView.setTag(handler);
        }else{
            handler = (Handler) convertView.getTag();
        }
        MyCariBean myCariBean = list.get(position);
        ImageLoader.getInstance().displayImage(myCariBean.getCoverImg(),handler.iv_caricature,options);
        handler.caricature_name.setText(myCariBean.getName());
        handler.caricature_address.setText(myCariBean.getArea());
        handler.caricature_lx.setText(myCariBean.getType());
        handler.caricature_time.setText(myCariBean.getLastUpdate()+"");

        return convertView;
    }
    class Handler{
        ImageView iv_caricature;
        TextView caricature_name;
        TextView caricature_address;
        TextView caricature_lx;
        TextView caricature_time;

    }
}
