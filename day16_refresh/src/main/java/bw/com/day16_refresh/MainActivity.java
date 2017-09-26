package bw.com.day16_refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener, PullToRefreshBase.OnLastItemVisibleListener {

    private List<String> list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            //加载完后停止
            pulist.onRefreshComplete();


        }
    };
    private MyAdapter adapter;
    private PullToRefreshListView pulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        pulist = (PullToRefreshListView) findViewById(R.id.pulist);
        adapter = new MyAdapter();
        pulist.setAdapter(adapter);
        //下拉刷新监听
        pulist.setOnRefreshListener(this);
        pulist.setOnLastItemVisibleListener(this);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("item"+i);
        }

    }
    //下拉刷新会回调
    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
      handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              list.add(0,"我是新加的数据");
              //发送一个消息
              handler.sendEmptyMessage(0);
          }
      },2000);
    }

    @Override
    public void onLastItemVisible() {
        Toast.makeText(this,"到底了,需要加载数据",Toast.LENGTH_SHORT).show();
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
            tv.setText(list.get(position));
            return tv;
        }
    }
}
