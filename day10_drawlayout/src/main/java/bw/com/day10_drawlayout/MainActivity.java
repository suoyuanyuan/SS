package bw.com.day10_drawlayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list;
    private ListView lv;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        lv = (ListView) findViewById(R.id.lv);
        FrameLayout fl = (FrameLayout) findViewById(R.id.fl);
        dl = (DrawerLayout) findViewById(R.id.dl);
        lv.setAdapter(new MyAdapter());
        //侧滑的item的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentFragment fragment = new ContentFragment();

                Bundle bundle = new Bundle();
                bundle.putString("text",list.get(position));

                fragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment).commit();

                //关闭
                dl.closeDrawer(lv);
            }

        });
    }
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("女神"+i);
        }
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
