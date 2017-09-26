package com.bw.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import bw.com.donghuacheng.R;

/**
 * Created by 索园 on 2017/8/18.
 */
public class HomeVpAdapter extends PagerAdapter{
    private Context context;
    private int[] ids;
    public HomeVpAdapter(Context context,int[] ids){
        this.context=context;
        this.ids=ids;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.home_vp_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        img.setImageResource(ids[position%ids.length]);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
