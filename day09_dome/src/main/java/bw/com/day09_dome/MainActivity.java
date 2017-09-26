package bw.com.day09_dome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String path="http://www.meirixue.com/api.php?c=index&a=index";
    private List<Bean.DataBean.AdlistBean> adlist;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            Gson gson=new Gson();
            Bean bean = gson.fromJson(json, Bean.class);
            //得到viewpager上面的数据
            adlist =  bean.getData().getAdlist();
            vp.setAdapter(new MyPagerAdapter());
            Toast.makeText(MainActivity.this,json,Toast.LENGTH_LONG).show();
        }
    };
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        vp = (ViewPager) findViewById(R.id.vp);
        GridView gv = (GridView) findViewById(R.id.gv);
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        int len=0;
                        byte[] buffer=new byte[1024];
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);

                        }
                        String json = baos.toString();
                        Message msg=new Message();
                        msg.obj=json;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return adlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=new ImageView(MainActivity.this);
            ImageLoader.getInstance().displayImage(adlist.get(position).getImg(),iv);
            container.addView(iv);
            return iv;
        }
    }
}
