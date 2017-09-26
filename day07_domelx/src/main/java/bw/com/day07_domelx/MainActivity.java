package bw.com.day07_domelx;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ByteArrayOutputStream baos;
    private List<Bean.DataBean.AdlistBean> adlist;
    String path="http://www.meirixue.com/api.php?c=index&a=index";
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            //在这里进行解析
            Gson gson=new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            adlist = bean.getData().getAdlist();
            Toast.makeText(MainActivity.this,adlist.get(0).getName(),Toast.LENGTH_SHORT).show();
            lv.setAdapter(new MyAdapter());
        }
    };
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        initData();

    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    baos=  new ByteArrayOutputStream();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        int len;
                        byte[] buffer=new byte[1024];
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        Message msg=new Message();
                        msg.obj= baos.toString();
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return adlist.size();
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
                convertView=  View.inflate(MainActivity.this,R.layout.item,null);
            }
            ImageView iv = (ImageView) convertView.findViewById(R.id.lv_iv);
            TextView tv1 = (TextView) convertView.findViewById(R.id.lv_tv1);
            TextView tv2 = (TextView) convertView.findViewById(R.id.lv_tv2);
            //展示图片
            ImageLoader.getInstance().displayImage(adlist.get(position).getImg(),iv);
            tv1.setText(adlist.get(position).getName());
            tv2.setText(adlist.get(position).getTitle());
            return convertView;
        }
    }
}
