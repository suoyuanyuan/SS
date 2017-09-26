package com.bw.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.DataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 索园 on 2017/8/15.
 */
public class MyAdapter extends PagerAdapter {
    List<DataBean.Adlist> adlist;
    Context context;
    public MyAdapter(List<DataBean.Adlist> adlist,Context context){
        this.adlist=adlist;
        this.context=context;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv=new ImageView(context);
        //对图片进行缩放
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(adlist.get(position%adlist.size()).img,iv);
        container.addView(iv);
        return iv;
    }
}
