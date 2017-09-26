package bw.com.suoyuanyuan20170821;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.NetWorkutils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int num = msg.what;
        switch (num){
            case 0:
                String json = (String) msg.obj;
                GsonData(json);
                break;
            case 1:
                //得到当前viewpager的索引
                int currentItem = vp.getCurrentItem();
                currentItem++;
                vp.setCurrentItem(currentItem);
                sendmesg();
                break;
        }
    }

};
    private ViewPager vp;
    private List<Data.DataBean.EssayBean> list;
    private GridView gv;
    private List<ImageView> views;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(){
            @Override
            public void run() {
                super.run();
                String json = NetWorkutils.getjson();
                Message msg=new Message();
                msg.obj=json;
                msg.what=0;
                handler.sendMessage(msg);
            }
        }.start();
        //获取ID控件
        vp = (ViewPager) findViewById(R.id.vp);
        gv = (GridView) findViewById(R.id.gv);
        ll = (LinearLayout) findViewById(R.id.ll);
    }
    private void GsonData(String json) {
        Gson gson=new Gson();
        Data data = gson.fromJson(json, Data.class);
        //得到所有数据
        list = data.getData().getEssay();
        //创建适配器
        MyAdapter adapter=new MyAdapter(list,MainActivity.this);
        vp.setAdapter(adapter);
        //让viewpager的索引默认的值在10000
        vp.setCurrentItem(10000);
        //自动轮播
        sendmesg();
        bb();
        aa();
        cc();
    }
    //滑动监听
    private void cc() {
      vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              
          }

          @Override
          public void onPageSelected(int position) {
              for (int i = 0; i <views.size() ; i++) {
                 if(position%list.size()==i){
                     views.get(position%list.size()).setImageResource(R.drawable.yes_fint);
                 }else {
                     views.get(i).setImageResource(R.drawable.no_fint);
                 }
              }

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });
    }
    //画圆点
    private void aa() {
        views = new ArrayList<>();
        if(views!=null){
            views.clear();
        }
        for (int i = 0; i <list.size() ; i++) {
            ImageView iv=new ImageView(MainActivity.this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);
            params.setMargins(10,0,10,0);
            ll.addView(iv,params);
            views.add(iv);

        }

    }

    private void bb() {

        Myadapter ada=new Myadapter();
        gv.setAdapter(ada);
    }

    //自动轮播
    private void sendmesg() {
        handler.sendEmptyMessageDelayed(1,2000);
    }
    class Myadapter extends BaseAdapter{
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
            if(convertView==null){
                convertView= View.inflate(MainActivity.this, R.layout.item, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            ImageView vv = (ImageView) convertView.findViewById(R.id.vv);
            ImageLoader.getInstance().displayImage(list.get(position).getAuthor().get(0).getWeb_url(),vv);
            tv.setText(list.get(position).getAuthor().get(0).getUser_name());
            return convertView;
        }
    }
}
