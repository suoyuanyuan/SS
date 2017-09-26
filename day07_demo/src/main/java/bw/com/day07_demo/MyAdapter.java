package bw.com.day07_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by 索园 on 2017/8/8.
 */
public class MyAdapter extends BaseAdapter {
    private List<Bean.DataBean.AdlistBean> list;
    private Context context;
    private LayoutInflater inflater;
    public MyAdapter(List<Bean.DataBean.AdlistBean> list,Context context){
        this.list=list;
        this.context=context;
        inflater=LayoutInflater.from(context);
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
        View view = inflater.inflate(R.layout.item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.tv2);
        //使用ImageLoader之前先初始化一下，如下所示
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
        //获取图片
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),iv);
        tv1.setText(list.get(position).getTitle());
        tv2.setText(list.get(position).getName());
        return view;
    }
}
