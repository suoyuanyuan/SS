package bw.com.day09_xlist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bw.com.day09_xlist.com.bw.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private List<String> list;
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //刷新适配器
        adapter.notifyDataSetChanged();
        close();
    }
};
    private MyAdapter adapter;
    private XListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (XListView) findViewById(R.id.lv);
        initData();
        //上拉
        lv.setPullLoadEnable(true);
        //下拉
        lv.setPullRefreshEnable(true);
        lv.setXListViewListener(this);
        View view = View.inflate(MainActivity.this, R.layout.headerview, null);
        ViewPager vp = (ViewPager) view.findViewById(R.id.vp);
        vp.setAdapter(new MyAdapte());
        lv.addHeaderView(view);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        /////////////////////////////////

    }
class MyAdapte extends PagerAdapter{
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv=new ImageView(MainActivity.this);
        iv.setImageResource(R.mipmap.ic_launcher);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}


    private void initData() {
        list = new ArrayList<>();
        for (int x=0;x<10;x++){
            list.add(0,"给我一个妹子");
        }
    }
    public void close(){
        //停止加载更多
        lv.stopLoadMore();
        //停止刷新
        lv.stopRefresh();

        lv.setRefreshTime("2017:8:10");
    }
    /*
            下拉刷新时会调用此方法
         */
    @Override
    public void onRefresh() {
        Toast.makeText(this,"下拉刷新",Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("女神");
                handler.sendEmptyMessage(0);
            }
        },2000);
    }
    /**
     * 加载更多时会调用此方法
     */
    @Override
    public void onLoadMore() {
        Toast.makeText(this,"加载更多",Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("淑女");
                handler.sendEmptyMessage(0);
            }
        },2000);
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv=new TextView(MainActivity.this);
            tv.setTextSize(25);
            tv.setText(list.get(position));
            return tv;
        }
    }
}
