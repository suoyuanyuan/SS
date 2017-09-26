package bw.com.day17_sp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<Bean.DataBean.AdlistBean> list = (List<Bean.DataBean.AdlistBean>) msg.obj;
            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .build();
            ImageLoader.getInstance().displayImage(list.get(0).getImg(),iv,options);
            tv.setText(list.get(0).getName());
        }
    };
    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        if(sp.getString("json",null).equals(null)){
            //没进行缓存
            Toast.makeText(MainActivity.this,"aa",Toast.LENGTH_SHORT).show();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String json = NetWorkUtils.getJson();
                    sp.edit().putString("json",json).commit();
                    //解析
                    gsonData(json);
                }
            }.start();
        }else {
            String json = sp.getString("json", null);
            gsonData(json);
        }
    }
    private void gsonData(String json) {

        Gson gson=new Gson();
        Bean bean = gson.fromJson(json, Bean.class);
        List<Bean.DataBean.AdlistBean> adlist = bean.getData().getAdlist();
        Message msg=new Message();
        msg.obj=adlist;
        handler.sendMessage(msg);
    }
}
