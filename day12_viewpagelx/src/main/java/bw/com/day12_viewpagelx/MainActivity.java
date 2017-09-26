package bw.com.day12_viewpagelx;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.DataBean;
import com.bw.adpter.MyAdapter;
import com.bw.utils.NetWorkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int num=msg.what;
        switch (num){
            case 0:
                String json = (String) msg.obj;
                GsonData(json);
               // Toast.makeText(this,json,Toast.LENGTH_SHORT).show();
                break;
            case 1:
                //得到当前viewpager的索引
                int position = vp.getCurrentItem();
                position++;
                vp.setCurrentItem(position);
                sendMsg();
                break;
        }

    }
};
    private ViewPager vp;
    private List<DataBean.Adlist> adlist;
    private LinearLayout ll;
    private List<ImageView> ivlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                super.run();
                String json = NetWorkUtils.getjson();
                Message msg=new Message();
                msg.obj=json;
                msg.what=0;
                handler.sendMessage(msg);
            }
        }.start();


        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);
    }
    private void GsonData(String json) {
        Gson gson=new Gson();
        DataBean dataBean = gson.fromJson(json, DataBean.class);
        adlist = dataBean.data.adlist;
        MyAdapter adapter=new MyAdapter(adlist,MainActivity.this);
        initData();

        vp.setAdapter(adapter);
        vp.setCurrentItem(10000);
        //自动轮播
        sendMsg();
        ViewPagerListener();
    }

    private void ViewPagerListener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i <ivlist.size() ; i++) {
                    if(position%adlist.size()==i){
                        //证明点和图片是对应的，应该点是变红的
                        ivlist.get(position%adlist.size()).setImageResource(R.drawable.dot_focuable);
                    }else {
                        ivlist.get(i).setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化点
    private void initData() {
        ivlist = new ArrayList<>();
        if(ivlist!=null){
            ivlist.clear();
        }
        for (int i = 0; i <adlist.size() ; i++) {
            ImageView iv=new ImageView(MainActivity.this);
            if(i==0){
                //第一张，让其圆点进行选中状态
                iv.setImageResource(R.drawable.dot_focuable);
            }else{
                iv.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);
            params.setMargins(10,0,10,0);
            ll.addView(iv,params);
            ivlist.add(iv);
        }
    }

    private void sendMsg() {
        handler.sendEmptyMessageDelayed(1,2000);
    }
}
