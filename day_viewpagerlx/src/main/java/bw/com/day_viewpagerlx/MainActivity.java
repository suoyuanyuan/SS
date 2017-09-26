package bw.com.day_viewpagerlx;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int num = msg.what;
        switch (num){
            case 0:
                String getjson = (String) msg.obj;
                GsonData(getjson);
                Toast.makeText(MainActivity.this,getjson,Toast.LENGTH_LONG).show();
                break;
            case 1:
                int position = vp.getCurrentItem();
                position++;
                vp.setCurrentItem(position);
                sendmsg();
                break;
        }
    }
};
    private List<DataBean.Adlist> adlist;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        vp = (ViewPager) findViewById(R.id.vp);
        new Thread(){
            @Override
            public void run() {
                super.run();
                String getjson = Networks.getjson();
                Message msg=new Message();
                msg.what=0;
                msg.obj=getjson;
                handler.sendMessage(msg);
            }
        }.start();
    }
    private void GsonData(String getjson) {

        Gson gson=new Gson();
        DataBean dataBean = gson.fromJson(getjson, DataBean.class);
        adlist = dataBean.data.adlist;
        MyAdapter adapter=new MyAdapter(adlist,MainActivity.this);
        vp.setAdapter(adapter);
        //让viewpager的索引默认的指在100000
        vp.setCurrentItem(100000);
        sendmsg();

    }

    private void sendmsg() {
        handler.sendEmptyMessageDelayed(1,2000);
    }
}
