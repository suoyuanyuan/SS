package bw.com.day09_rikao;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import bw.com.day09_rikao.com.bw.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private List<String> list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();

        }
    };
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        XListView lv = (XListView) findViewById(R.id.lv);
        //上拉
        lv.setPullLoadEnable(true);
        //下拉
        lv.setPullRefreshEnable(true);

        lv.setXListViewListener(this);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("条目"+i);
        }
    }
//下拉刷新时会调用此方法
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("条目n");
                handler.sendEmptyMessage(0);
            }
        },2000);
    }
//加载更多时会调用此方法
    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("条目x");
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
