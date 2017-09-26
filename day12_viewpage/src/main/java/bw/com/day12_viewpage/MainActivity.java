package bw.com.day12_viewpage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
                Toast.makeText(MainActivity.this,json,Toast.LENGTH_LONG).show();
                break;
            case 1:
                //得到当前viewpager的索引
                int position = vp.getCurrentItem();
                //让当前的viewpager加1
                position++;
                vp.setCurrentItem(position);
                sendMse();
                break;
        }

    }


};
    private ViewPager vp;
    private List<DataBean.Adlist> adlist;
    private LinearLayout ll;
    private ImageView iv;
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
                msg.what=0;
                msg.obj=json;
                handler.sendMessage(msg);
            }
        }.start();


        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);

    }
    private void GsonData(String json) {
        Gson gson=new Gson();
        DataBean dataBean = gson.fromJson(json, DataBean.class);
        //得到所有的数据
        adlist = dataBean.data.adlist;
        //把数据传到adapter里面
        MyAdapter adapter=new MyAdapter(adlist,MainActivity.this);
        vp.setAdapter(adapter);
        //初始化点
        initDot();

        //让viewpager的索引默认的指在100000
        vp.setCurrentItem(100000);
        sendMse();
        //viewpager的监听事件
        Viewpagerlistener();

    }
    //viewpager的监听事件
    private void Viewpagerlistener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i <ivlist.size() ; i++) {
                    if(position%adlist.size()==i){

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

    private void initDot() {
        ivlist = new ArrayList<>();
        if (ivlist !=null){
            ivlist.clear();
        }

        for (int i = 0; i <adlist.size() ; i++) {
            iv = new ImageView(MainActivity.this);
            if(i==0){
                //第一张，让其圆点进行选中状态
                iv.setImageResource(R.drawable.dot_focuable);

            }else{
                //让其他的都不选中
                iv.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams parmes=new LinearLayout.LayoutParams(20,20);
            //设置两个点之间的距离
            parmes.setMargins(10,0,10,0);
            ll.addView(iv,parmes);
            ivlist.add(iv);
        }

    }

    //自动轮播
    private void sendMse() {
        handler.sendEmptyMessageDelayed(1,2000);
    }

}
