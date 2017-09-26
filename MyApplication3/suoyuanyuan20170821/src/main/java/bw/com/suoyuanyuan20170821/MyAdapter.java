package bw.com.suoyuanyuan20170821;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 索园 on 2017/8/21.
 */
public class MyAdapter extends PagerAdapter {
    List<Data.DataBean.EssayBean> list;
    Context context;
    public MyAdapter(List<Data.DataBean.EssayBean> list,Context context){
        this.list=list;
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
        ImageLoader.getInstance().displayImage(list.get(position%list.size()).getAuthor().get(0).getWeb_url(),iv);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(iv);
        return iv;
    }
}
