package bw.com.yuekaolx2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 索园 on 2017/8/24.
 */
public class MyAdapter extends BaseAdapter {
    private List<Bean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;
    public MyAdapter(List<Bean.DataBean> list,Context context){
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean b1 = list.get(position).isHas_image();
        if(b1==false){
            convertView=inflater.inflate(R.layout.item,null);
            TextView tv = (TextView) convertView.findViewById(R.id.lv_tv);
            tv.setText(list.get(position).getTitle());
        }else{
            convertView = inflater.inflate(R.layout.item2, null);
            ImageView iv1 = (ImageView) convertView.findViewById(R.id.lv_iv1);
            ImageView iv2 = (ImageView) convertView.findViewById(R.id.lv_iv2);
            ImageView iv3 = (ImageView) convertView.findViewById(R.id.lv_iv3);
            ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(0).getUrl(),iv1);
            ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(1).getUrl(),iv2);
            ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(2).getUrl(),iv3);
        }
        return convertView;
    }
}
