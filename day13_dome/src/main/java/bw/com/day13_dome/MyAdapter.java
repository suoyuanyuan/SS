package bw.com.day13_dome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 索园 on 2017/8/17.
 */
public class MyAdapter extends BaseAdapter{
    private List<Bean.ArrayBean> list;
    private Context context;
    private LayoutInflater inflater;
    public MyAdapter(List<Bean.ArrayBean> list,Context context){
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
        ViewHolder holder=new ViewHolder();
        convertView= inflater.inflate(R.layout.item, null);
        holder.tv = (TextView) convertView.findViewById(R.id.tv);
        holder.iv= (ImageView) convertView.findViewById(R.id.iv);
        convertView.setTag(holder);
        holder.tv.setText(list.get(position).getTitle());
        return convertView;
    }
    class ViewHolder{
        TextView tv;
        ImageView iv;
    }
}
